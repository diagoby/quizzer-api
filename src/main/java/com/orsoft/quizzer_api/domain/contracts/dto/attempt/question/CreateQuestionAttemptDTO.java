package com.orsoft.quizzer_api.domain.contracts.dto.attempt.question;

import com.orsoft.quizzer_api.domain.contracts.constraints.UUIDString;
import com.orsoft.quizzer_api.domain.contracts.dto.attempt.answer.CreateAnswerAttemptDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class CreateQuestionAttemptDTO {
  @NotBlank
  @UUIDString
  public String id;

  @Valid
  @NotEmpty
  public Set<CreateAnswerAttemptDTO> answers;

  private CreateQuestionAttemptDTO() { }

  public static CreateQuestionAttemptDTO.Builder builder() {
    return new CreateQuestionAttemptDTO().new Builder();
  }

  public class Builder {
    private Builder() {}

    public CreateQuestionAttemptDTO.Builder withId(String id) {
      CreateQuestionAttemptDTO.this.id = id;

      return this;
    }

    public CreateQuestionAttemptDTO.Builder withAnswers(Set<CreateAnswerAttemptDTO> answers) {
      CreateQuestionAttemptDTO.this.answers = answers;

      return this;
    }

    public CreateQuestionAttemptDTO build() {
      return CreateQuestionAttemptDTO.this;
    }
  }
}
