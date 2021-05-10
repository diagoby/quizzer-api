package com.orsoft.quizzer_api.domain.contracts.mappers;

import com.orsoft.quizzer_api.domain.contracts.dto.question.ReadQuestionDTO;
import com.orsoft.quizzer_api.domain.models.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuestionMapper implements IEntityMapper<Question, Object, ReadQuestionDTO>{
  @Autowired
  private AnswerMapper answerMapper;

  @Override
  public Question toEntity(Object dto) {
    return null;
  }

  @Override
  public ReadQuestionDTO toDTO(Question entity) {
    ReadQuestionDTO dto = ReadQuestionDTO.builder()
      .withId(entity.getId().toString())
      .withTitle(entity.getTitle())
      .withType(entity.getType().toString())
      .withAnswers(
        entity.getAnswers().stream()
          .map(this.answerMapper::toDTO)
          .collect(Collectors.toSet())
      )
      .build();

    return dto;
  }
}
