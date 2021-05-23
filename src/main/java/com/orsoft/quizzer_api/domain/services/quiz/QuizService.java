package com.orsoft.quizzer_api.domain.services.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.quiz.CreateQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.mappers.QuizMapper;
import com.orsoft.quizzer_api.domain.models.quiz.Quiz;
import com.orsoft.quizzer_api.domain.models.user.User;
import com.orsoft.quizzer_api.domain.utils.Result;
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
    Optional<User> userEntity = userRepository.findById(UUID.fromString(quizDto.creatorId));

    if(userEntity.isEmpty()) {
      return Result.error(String.format("No user found with id \"%s\"", quizDto.creatorId));
    }

    Quiz quizEntity = quizMapper.toEntity(quizDto);
    quizEntity.setUser(userEntity.get());

    quizRepository.save(quizEntity);

    return Result.empty();
  }

  @Override
  public Result<ReadQuizDTO, String> getQuizById(String id) {
    Optional<ReadQuizDTO> readQuizDto = quizRepository.findById(UUID.fromString(id))
      .map(quizMapper::toDTO);

    return Result.ofOptional(readQuizDto, String.format("No quiz found by id \"%s\"", id));
  }

  @Override
  public Result<Set<ReadQuizDTO>, String> getUserQuizzes(String userId) {
    Optional<User> userEntity = userRepository.findById(UUID.fromString(userId));

    if(userEntity.isEmpty()) {
      return Result.error(String.format("No user found with id \"%s\"", userId));
    }

    return Result.ok(
      userEntity.get().getQuizzes().stream()
        .map(quizMapper::toDTO)
        .collect(Collectors.toSet())
    );
  }
}
