package com.orsoft.quizzer_api.domain.services.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.attempt.CreateAttemptDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.CreateQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.mappers.QuizMapper;
import com.orsoft.quizzer_api.domain.models.answer.Answer;
import com.orsoft.quizzer_api.domain.models.attempt.Attempt;
import com.orsoft.quizzer_api.domain.models.attempt.AttemptAnswer;
import com.orsoft.quizzer_api.domain.models.question.Question;
import com.orsoft.quizzer_api.domain.models.question.QuestionType;
import com.orsoft.quizzer_api.domain.models.quiz.Quiz;
import com.orsoft.quizzer_api.domain.models.user.User;
import com.orsoft.quizzer_api.domain.utils.Convert;
import com.orsoft.quizzer_api.domain.utils.Result;
import com.orsoft.quizzer_api.infrastructure.repositories.IAttemptRepository;
import com.orsoft.quizzer_api.infrastructure.repositories.IQuizRepository;
import com.orsoft.quizzer_api.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService implements IQuizService {
  @Autowired
  private IQuizRepository quizRepository;

  @Autowired
  private IAttemptRepository attemptRepository;

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private QuizMapper quizMapper;

  @Override
  public Result<Object, String> createQuiz(CreateQuizDTO quizDto) {
    Optional<User> userEntity = Convert.stringToUUID(quizDto.creatorId)
      .flatMap(userRepository::findById);

    if(userEntity.isEmpty()) {
      return Result.error("Could not find user with the given id. \nPlease, make sure the creatorId is correct.");
    }

    Quiz quizEntity = quizMapper.toEntity(quizDto);
    quizEntity.setUser(userEntity.get());

    quizRepository.save(quizEntity);

    return Result.empty();
  }

  @Override
  public Result<ReadQuizDTO, String> getQuizById(String id) {
    return Result.ofOptional(
      Convert.stringToUUID(id)
        .flatMap(quizRepository::findById)
        .map(quizMapper::toDTO),
      "Could not find the quiz with the given id. \nPlease, make sure the id is correct."
    );
  }

  @Override
  public Result<Set<ReadQuizDTO>, String> getUserQuizzes(String userId) {
    Optional<User> userEntity = Convert.stringToUUID(userId)
      .flatMap(userRepository::findById);

    if(userEntity.isEmpty()) {
      return Result.error("Could not find user with the given id. \nPlease, make sure the id is correct.");
    }

    return Result.ok(
      userEntity.get().getQuizzes().stream()
        .map(quizMapper::toDTO)
        .collect(Collectors.toSet())
    );
  }

  @Override
  public Result<Object, String> makeQuizAttempt(String quizId, CreateAttemptDTO attemptDto) {
    Optional<Quiz> maybeQuizEntity = Convert.stringToUUID(quizId)
      .flatMap(quizRepository::findById);

    if(maybeQuizEntity.isEmpty()) {
      return Result.error("Could not find quiz with the given id. \nPlease, make sure the id is correct.");
    }

    Optional<User> maybeUserEntity = Convert.stringToUUID(attemptDto.userId)
      .flatMap(userRepository::findById);

    if(maybeUserEntity.isEmpty()) {
      return Result.error("Could not find user with the given id. \nPlease, make sure the userId is correct.");
    }

    Quiz quizEntity = maybeQuizEntity.get();
    User userEntity = maybeUserEntity.get();

    Attempt attemptEntity = new Attempt();
    attemptEntity.setQuiz(quizEntity);
    attemptEntity.setUser(userEntity);

    Map<UUID, Set<AttemptAnswer>> questionsAttemptsAnswersMap = new HashMap<>();

    for (var questionAttemptDto : attemptDto.questions) {
      Optional<Question> maybeQuestionEntity = quizEntity.getQuestions().stream()
        .filter(question -> Convert.stringToUUID(questionAttemptDto.id)
          .map(question.getId()::equals).orElse(false))
        .findFirst();

      if(maybeQuestionEntity.isEmpty()) {
        return Result.error(String.format("Could not find question with id: %s", questionAttemptDto.id));
      }

      Question questionEntity = maybeQuestionEntity.get();
      Set<AttemptAnswer> attemptAnswers = new HashSet<>();

      if(questionAttemptDto.answers.size() > questionEntity.getPossibleAnswersAmount()) {
        return Result.error(String.format("Invalid answers format for question with id: %s", questionAttemptDto.id));
      }

      for (var answerAttemptDto : questionAttemptDto.answers) {
        AttemptAnswer attemptAnswerEntity = new AttemptAnswer();
        attemptAnswerEntity.setQuestion(questionEntity);

        if(!questionEntity.getType().equals(QuestionType.TEXT)) {
          Optional<Answer> maybeAnswer = questionEntity.getAnswers().stream()
            .filter(answer -> Convert.stringToUUID(answerAttemptDto.id)
              .map(answer.getId()::equals).orElse(false))
            .findFirst();

          if(maybeAnswer.isEmpty()) {
            return Result.error(String.format("Could not find answer with id: %s", answerAttemptDto.id));
          }

          attemptAnswerEntity.setAnswer(maybeAnswer.get());
        } else {
          attemptAnswerEntity.setValue(answerAttemptDto.value);
        }

        attemptAnswers.add(attemptAnswerEntity);
      }

      /* If question repeats, replace old answers with new ones */
      questionsAttemptsAnswersMap.put(questionEntity.getId(), attemptAnswers);
    }

    Set<AttemptAnswer> flattenAttemptAnswers = questionsAttemptsAnswersMap.values().stream()
      .flatMap(Set::stream)
      .collect(Collectors.toSet());

    attemptEntity.setAnswers(flattenAttemptAnswers);

    attemptRepository.save(attemptEntity);

    return Result.empty();
  }
}
