package com.orsoft.quizzer_api.domain.contracts.dto.attempt;

import com.orsoft.quizzer_api.domain.contracts.constraints.UUIDString;
import com.orsoft.quizzer_api.domain.contracts.dto.attempt.question.CreateQuestionAttemptDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class CreateAttemptDTO {
  @NotBlank
  @UUIDString
  public String userId;

  @Valid
  @NotEmpty
  public Set<CreateQuestionAttemptDTO> questions;

  private CreateAttemptDTO() { }

  public static CreateAttemptDTO.Builder builder() {
    return new CreateAttemptDTO().new Builder();
  }

  public class Builder {
    private Builder() {}

    public CreateAttemptDTO.Builder withUserId(String userId) {
      CreateAttemptDTO.this.userId = userId;

      return this;
    }

    public CreateAttemptDTO.Builder withQuestions(Set<CreateQuestionAttemptDTO> questions) {
      CreateAttemptDTO.this.questions = questions;

      return this;
    }

    public CreateAttemptDTO build() {
      return CreateAttemptDTO.this;
    }
  }
}
