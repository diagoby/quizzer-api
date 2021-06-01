package com.orsoft.quizzer_api.domain.contracts.mappers;

import com.orsoft.quizzer_api.domain.contracts.dto.quiz.CreateQuizDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.quiz.ReadQuizDTO;
import com.orsoft.quizzer_api.domain.models.quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuizMapper implements IEntityMapper<Quiz, CreateQuizDTO, ReadQuizDTO>{
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private QuestionMapper questionMapper;

  @Override
  public Quiz toEntity(CreateQuizDTO dto) {
    Quiz quizEntity = new Quiz();

    quizEntity.setTitle(dto.title);
    quizEntity.setDescription(dto.description);
    quizEntity.setQuestions(
      dto.questions.stream()
        .map(questionMapper::toEntity)
        .collect(Collectors.toSet())
    );

    return quizEntity;
  }

  @Override
  public ReadQuizDTO toDTO(Quiz entity) {
    ReadQuizDTO dto = ReadQuizDTO.builder()
      .withId(entity.getId().toString())
      .withTitle(entity.getTitle())
      .withDescription(entity.getDescription())
      .withCreatedAt(entity.getCreatedAt())
      .withCreatedBy(userMapper.toDTO(entity.getUser()))
      .withQuestions(
        entity.getQuestions().stream()
          .map(questionMapper::toDTO)
          .collect(Collectors.toSet())
      )
      .build();

    return dto;
  }
}
