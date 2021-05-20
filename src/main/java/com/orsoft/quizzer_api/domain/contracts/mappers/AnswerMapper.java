package com.orsoft.quizzer_api.domain.contracts.mappers;

import com.orsoft.quizzer_api.domain.contracts.dto.answer.CreateAnswerDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.answer.ReadAnswerDTO;
import com.orsoft.quizzer_api.domain.models.answer.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper implements IEntityMapper<Answer, CreateAnswerDTO, ReadAnswerDTO>{

  @Override
  public Answer toEntity(CreateAnswerDTO dto) {
    Answer entity = new Answer();

    entity.setTitle(dto.title);
    entity.setRight(dto.isRight);

    return entity;
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
