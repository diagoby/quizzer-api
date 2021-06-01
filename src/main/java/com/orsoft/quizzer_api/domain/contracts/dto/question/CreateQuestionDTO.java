package com.orsoft.quizzer_api.domain.contracts.dto.question;

import com.orsoft.quizzer_api.domain.contracts.constraints.Enumerated;
import com.orsoft.quizzer_api.domain.contracts.dto.answer.CreateAnswerDTO;
import com.orsoft.quizzer_api.domain.models.question.QuestionType;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class CreateQuestionDTO {
  @NotBlank
  public String title;

  @Enumerated(enumClass = QuestionType.class)
  public String type;

  @Valid
  @NotEmpty
  public Set<CreateAnswerDTO> answers;

  private CreateQuestionDTO() { }

  public static Builder builder() {
    return new CreateQuestionDTO().new Builder();
  }

  public class Builder {
    private Builder() {}

    public Builder withTitle(String title) {
      CreateQuestionDTO.this.title = title;

      return this;
    }

    public Builder withType(String type) {
      CreateQuestionDTO.this.type = type;

      return this;
    }

    public Builder withAnswers(Set<CreateAnswerDTO> answers) {
      CreateQuestionDTO.this.answers = answers;

      return this;
    }

    public CreateQuestionDTO build() {
      return CreateQuestionDTO.this;
    }
  }
}
