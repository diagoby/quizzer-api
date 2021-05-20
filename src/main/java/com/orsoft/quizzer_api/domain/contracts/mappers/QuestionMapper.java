package com.orsoft.quizzer_api.domain.contracts.mappers;

import com.orsoft.quizzer_api.domain.contracts.dto.question.CreateQuestionDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.question.ReadQuestionDTO;
import com.orsoft.quizzer_api.domain.models.question.Question;
import com.orsoft.quizzer_api.domain.models.question.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuestionMapper implements IEntityMapper<Question, CreateQuestionDTO, ReadQuestionDTO>{
  @Autowired
  private AnswerMapper answerMapper;

  @Override
  public Question toEntity(CreateQuestionDTO dto) {
    Question questionEntity = new Question();

    questionEntity.setTitle(dto.title);
    questionEntity.setType(QuestionType.valueOf(dto.type));
    questionEntity.setAnswers(
      dto.answers.stream()
        .map(answerMapper::toEntity)
        .collect(Collectors.toSet())
    );

    return questionEntity;
  }

  @Override
  public ReadQuestionDTO toDTO(Question entity) {
    ReadQuestionDTO dto = ReadQuestionDTO.builder()
      .withId(entity.getId().toString())
      .withTitle(entity.getTitle())
      .withType(entity.getType().toString())
      .withAnswers(
        entity.getAnswers().stream()
          .map(answerMapper::toDTO)
          .collect(Collectors.toSet())
      )
      .build();

    return dto;
  }
}
