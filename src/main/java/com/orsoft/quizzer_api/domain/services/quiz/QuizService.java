package com.orsoft.quizzer_api.domain.services.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.mappers.QuizMapper;
import com.orsoft.quizzer_api.domain.utils.Result;
import com.orsoft.quizzer_api.infrastructure.repositories.IQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuizService implements IQuizService {
  @Autowired
  private IQuizRepository quizRepository;

  @Autowired
  private QuizMapper quizMapper;
  
  @Override
  public Result<ReadQuizDTO, String> getQuizById(String id) {
    Optional<ReadQuizDTO> readQuizDto = quizRepository.findById(UUID.fromString(id))
      .map(this.quizMapper::toDTO);

    return Result.ofOptional(readQuizDto, String.format("No quiz found by id \"%s\"", id));
  }
}
