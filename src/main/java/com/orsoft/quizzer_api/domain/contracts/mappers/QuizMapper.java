package com.orsoft.quizzer_api.domain.contracts.mappers;

import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.models.quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuizMapper implements IEntityMapper<Quiz, Object, ReadQuizDTO>{
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private QuestionMapper questionMapper;

  @Override
  public Quiz toEntity(Object dto) {
    return null;
  }

  @Override
  public ReadQuizDTO toDTO(Quiz entity) {
    ReadQuizDTO dto = ReadQuizDTO.builder()
      .withId(entity.getId().toString())
      .withTitle(entity.getTitle())
      .withDescription(entity.getDescription())
      .withCreatedAt(entity.getCreatedAt())
      .withCreatedBy(this.userMapper.toDTO(entity.getUser()))
      .withQuestions(
        entity.getQuestions().stream()
          .map(this.questionMapper::toDTO)
          .collect(Collectors.toSet())
      )
      .build();

    return dto;
  }
}
