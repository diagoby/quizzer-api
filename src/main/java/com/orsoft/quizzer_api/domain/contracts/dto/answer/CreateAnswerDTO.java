package com.orsoft.quizzer_api.domain.contracts.dto.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAnswerDTO {
  @NotBlank
  public String title;

  @NotNull
  public Boolean isRight;

  public CreateAnswerDTO(String title, Boolean isRight) {
    this.title = title;
    this.isRight = isRight;
  }
}
