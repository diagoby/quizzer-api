package com.orsoft.quizzer_api.domain.services.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.attempt.CreateAttemptDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.attempt.result.AttemptResultDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.attempt.result.AttemptResultQuestionDTO;
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
  public Result<Object, String> removeQuiz(String id) {
    Optional<UUID> existingQuizId = Convert.stringToUUID(id)
      .filter(quizRepository::existsById);

    if(existingQuizId.isEmpty()) {
      return Result.error("Could not find quiz with the given id. \nPlease, make sure the id is correct.");
    }

    quizRepository.deleteById(existingQuizId.get());

    return Result.empty();
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
  public Result<AttemptResultDTO, String> makeQuizAttempt(String quizId, CreateAttemptDTO attemptDto) {
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

    Attempt attemptEntityCandidate = new Attempt();
    attemptEntityCandidate.setQuiz(quizEntity);
    attemptEntityCandidate.setUser(userEntity);

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

    attemptEntityCandidate.setAnswers(flattenAttemptAnswers);

    Attempt attemptEntity = attemptRepository.save(attemptEntityCandidate);

    return getQuizAttemptResult(attemptEntity.getId().toString());
  }

  @Override
  public Result<AttemptResultDTO, String> getQuizAttemptResult(String attemptId) {
    Optional<Attempt> maybeAttemptEntity = Convert.stringToUUID(attemptId)
      .flatMap(attemptRepository::findById);

    if(maybeAttemptEntity.isEmpty()) {
      return Result.error(String.format("Could not find quiz attempt with id: %s", attemptId));
    }

    Attempt attemptEntity = maybeAttemptEntity.get();
    Quiz quizEntity = attemptEntity.getQuiz();

    Map<Question, Set<AttemptAnswer>> attemptAnswersByQuestion = attemptEntity.getAnswers().stream().collect(
      Collectors.groupingBy(AttemptAnswer::getQuestion, Collectors.toSet())
    );

    Set<AttemptResultQuestionDTO> questionResults = new HashSet<>();

    for (var entry : attemptAnswersByQuestion.entrySet()) {
      Question questionEntity = entry.getKey();
      Set<AttemptAnswer> attemptAnswers = entry.getValue();

      AttemptResultQuestionDTO resultQuestionDto = AttemptResultQuestionDTO.builder()
        .withQuestionType(questionEntity.getType().toString())
        .withQuestionTitle(questionEntity.getTitle())
        .build();

      if(questionEntity.getType() == QuestionType.TEXT) {
        String answerValue = attemptAnswers.stream().map(AttemptAnswer::getValue).findFirst().orElse("");

        resultQuestionDto.isCorrectQuestion = questionEntity.getCorrectAnswers().stream()
          .map(answer -> answer.getTitle().toLowerCase())
          .findFirst()
          .map(answerValue.toLowerCase()::equals)
          .orElse(false);

        resultQuestionDto.attemptAnswers = Set.of(answerValue);
      } else {
        Set<Answer> answers = attemptAnswers.stream().map(AttemptAnswer::getAnswer).collect(Collectors.toSet());

        resultQuestionDto.isCorrectQuestion = questionEntity.getCorrectAnswers().equals(answers);
        resultQuestionDto.attemptAnswers = answers.stream().map(Answer::getTitle).collect(Collectors.toSet());
      }

      questionResults.add(resultQuestionDto);
    }

    AttemptResultDTO attemptResultDto = AttemptResultDTO.builder()
      .withQuizTitle(quizEntity.getTitle())
      .withQuestionResults(questionResults)
      .build();

    return Result.ok(attemptResultDto);
  }
}
