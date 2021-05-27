package com.orsoft.quizzer_api.domain.services.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.quiz.CreateQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.mappers.QuizMapper;
import com.orsoft.quizzer_api.domain.models.quiz.Quiz;
import com.orsoft.quizzer_api.domain.models.user.User;
import com.orsoft.quizzer_api.domain.utils.Result;
import com.orsoft.quizzer_api.domain.utils.Convert;
import com.orsoft.quizzer_api.infrastructure.repositories.IQuizRepository;
import com.orsoft.quizzer_api.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuizService implements IQuizService {
  @Autowired
  private IQuizRepository quizRepository;

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
}
