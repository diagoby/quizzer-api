package com.orsoft.quizzer_api.domain.contracts.dto.quiz;

import com.orsoft.quizzer_api.domain.contracts.dto.question.ReadQuestionDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.user.ReadUserDTO;

import java.util.Date;
import java.util.Set;

public class ReadQuizDTO {
  public String id;
  public String title;
  public String description;
  public Date createdAt;
  public ReadUserDTO createdBy;
  public Set<ReadQuestionDTO> questions;

  private ReadQuizDTO() { }

  public static ReadQuizDTO.Builder builder() {
    return new ReadQuizDTO().new Builder();
  }

  public class Builder {
    private Builder() {}

    public ReadQuizDTO.Builder withId(String id) {
      ReadQuizDTO.this.id = id;

      return this;
    }

    public ReadQuizDTO.Builder withTitle(String title) {
      ReadQuizDTO.this.title = title;

      return this;
    }

    public ReadQuizDTO.Builder withDescription(String description) {
      ReadQuizDTO.this.description = description;

      return this;
    }

    public ReadQuizDTO.Builder withCreatedBy(ReadUserDTO createdBy) {
      ReadQuizDTO.this.createdBy = createdBy;

      return this;
    }

    public ReadQuizDTO.Builder withCreatedAt(Date createdAt) {
      ReadQuizDTO.this.createdAt = createdAt;

      return this;
    }

    public ReadQuizDTO.Builder withQuestions(Set<ReadQuestionDTO> questions) {
      ReadQuizDTO.this.questions = questions;

      return this;
    }

    public ReadQuizDTO build() {
      return ReadQuizDTO.this;
    }
  }
}
