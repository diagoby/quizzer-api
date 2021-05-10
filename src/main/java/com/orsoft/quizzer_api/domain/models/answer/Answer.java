package com.orsoft.quizzer_api.domain.models.answer;

import com.orsoft.quizzer_api.domain.models.question.Question;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "answers")
public class Answer {
  private UUID id = UUID.randomUUID();
  private String title;
  private Boolean isRight;

  private Question question;

  @Id
  @GeneratedValue
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Column(nullable = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(
    name = "is_right",
    nullable = false
  )
  public Boolean getRight() {
    return isRight;
  }

  public void setRight(Boolean right) {
    isRight = right;
  }

  @ManyToOne
  @JoinColumn(nullable = false)
  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }
}
