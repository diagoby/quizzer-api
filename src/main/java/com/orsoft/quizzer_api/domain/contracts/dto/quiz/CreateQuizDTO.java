package com.orsoft.quizzer_api.domain.contracts.dto.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.question.CreateQuestionDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class CreateQuizDTO {
  @NotBlank
  public String title;

  public String description;

  @NotBlank
  public String creatorId;

  @Valid
  @NotEmpty
  public Set<CreateQuestionDTO> questions;

  private CreateQuizDTO() { }

  public static CreateQuizDTO.Builder builder() {
    return new CreateQuizDTO().new Builder();
  }

  public class Builder {
    private Builder() {}

    public CreateQuizDTO.Builder withTitle(String title) {
      CreateQuizDTO.this.title = title;

      return this;
    }

    public CreateQuizDTO.Builder withDescription(String description) {
      CreateQuizDTO.this.description = description;

      return this;
    }

    public CreateQuizDTO.Builder withCreatorId(String creatorId) {
      CreateQuizDTO.this.creatorId = creatorId;

      return this;
    }

    public CreateQuizDTO.Builder withQuestions(Set<CreateQuestionDTO> questions) {
      CreateQuizDTO.this.questions = questions;

      return this;
    }

    public CreateQuizDTO build() {
      return CreateQuizDTO.this;
    }
  }
}
