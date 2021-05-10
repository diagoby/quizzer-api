package com.orsoft.quizzer_api.domain.contracts.mappers;

import com.orsoft.quizzer_api.domain.contracts.dto.answer.ReadAnswerDTO;
import com.orsoft.quizzer_api.domain.models.answer.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper implements IEntityMapper<Answer, Object, ReadAnswerDTO>{

  @Override
  public Answer toEntity(Object dto) {
    return null;
  }

  @Override
  public ReadAnswerDTO toDTO(Answer entity) {
    ReadAnswerDTO dto = new ReadAnswerDTO(
      entity.getId().toString(),
      entity.getTitle(),
      entity.getRight()
    );

    return dto;
  }
}
