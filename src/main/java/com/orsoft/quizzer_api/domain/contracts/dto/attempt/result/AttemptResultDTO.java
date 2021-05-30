package com.orsoft.quizzer_api.domain.contracts.dto.attempt.result;

import com.orsoft.quizzer_api.domain.contracts.dto.question.ReadQuestionDTO;

import java.util.Set;

public class AttemptResultDTO {
  public String quizTitle;
  public Set<AttemptResultQuestionDTO> questionResults;

  private AttemptResultDTO() { }

  public static AttemptResultDTO.Builder builder() {
    return new AttemptResultDTO().new Builder();
  }

  public class Builder {
    private Builder() {}

    public AttemptResultDTO.Builder withQuizTitle(String title) {
      AttemptResultDTO.this.quizTitle = title;

      return this;
    }

    public AttemptResultDTO.Builder withQuestionResults(Set<AttemptResultQuestionDTO> results) {
      AttemptResultDTO.this.questionResults = results;

      return this;
    }

    public AttemptResultDTO build() {
      return AttemptResultDTO.this;
    }
  }
}
