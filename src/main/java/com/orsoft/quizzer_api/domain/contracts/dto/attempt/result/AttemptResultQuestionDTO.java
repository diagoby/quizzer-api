package com.orsoft.quizzer_api.domain.contracts.dto.attempt.result;

import java.util.Set;

public class AttemptResultQuestionDTO {
  public String questionTitle;
  public String questionType;
  public boolean isCorrectQuestion;
  public Set<String> attemptAnswers;

  private AttemptResultQuestionDTO() { }

  public static AttemptResultQuestionDTO.Builder builder() {
    return new AttemptResultQuestionDTO().new Builder();
  }

  public class Builder {
    private Builder() {}

    public AttemptResultQuestionDTO.Builder withQuestionTitle(String title) {
      AttemptResultQuestionDTO.this.questionTitle = title;

      return this;
    }

    public AttemptResultQuestionDTO.Builder withQuestionType(String type) {
      AttemptResultQuestionDTO.this.questionType = type;

      return this;
    }

    public AttemptResultQuestionDTO.Builder withIsCorrectQuestion(boolean isCorrect) {
      AttemptResultQuestionDTO.this.isCorrectQuestion = isCorrect;

      return this;
    }

    public AttemptResultQuestionDTO.Builder withAttemptAnswers(Set<String> answers) {
      AttemptResultQuestionDTO.this.attemptAnswers = answers;

      return this;
    }

    public AttemptResultQuestionDTO build() {
      return AttemptResultQuestionDTO.this;
    }
  }
}
