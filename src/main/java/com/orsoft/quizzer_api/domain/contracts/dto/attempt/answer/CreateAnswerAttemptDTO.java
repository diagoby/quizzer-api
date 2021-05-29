package com.orsoft.quizzer_api.domain.contracts.dto.attempt.answer;

import com.orsoft.quizzer_api.domain.contracts.constraints.OneOf;
import com.orsoft.quizzer_api.domain.contracts.constraints.UUIDString;

@OneOf(peers = { "id", "value" })
public class CreateAnswerAttemptDTO {
  @UUIDString(allowNull = true)
  public String id;

  public String value;

  public CreateAnswerAttemptDTO(String id, String value) {
    this.id = id;
    this.value = value;
  }
}
