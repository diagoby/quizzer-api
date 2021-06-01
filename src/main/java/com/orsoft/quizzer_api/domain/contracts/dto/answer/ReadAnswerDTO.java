package com.orsoft.quizzer_api.domain.contracts.dto.answer;

public class ReadAnswerDTO {
  public String id;
  public String title;
  public Boolean isRight;

  public ReadAnswerDTO(String id, String title, Boolean isRight) {
    this.id = id;
    this.title = title;
    this.isRight = isRight;
  }
}
