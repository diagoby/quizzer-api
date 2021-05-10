package com.orsoft.quizzer_api.domain.contracts.dto.question;

import com.orsoft.quizzer_api.domain.contracts.dto.answer.ReadAnswerDTO;

import java.util.Set;

public class ReadQuestionDTO {
  public String id;
  public String title;
  public String type;

  public Set<ReadAnswerDTO> answers;

  private ReadQuestionDTO() { }

  public static Builder builder() {
    return new ReadQuestionDTO().new Builder();
  }

  public class Builder {
    private Builder() {}

    public Builder withId(String id) {
      ReadQuestionDTO.this.id = id;

      return this;
    }

    public Builder withTitle(String title) {
      ReadQuestionDTO.this.title = title;

      return this;
    }

    public Builder withType(String type) {
      ReadQuestionDTO.this.type = type;

      return this;
    }

    public Builder withAnswers(Set<ReadAnswerDTO> answers) {
      ReadQuestionDTO.this.answers = answers;

      return this;
    }

    public ReadQuestionDTO build() {
      return ReadQuestionDTO.this;
    }
  }
}
