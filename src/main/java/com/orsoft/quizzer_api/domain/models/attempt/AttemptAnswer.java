package com.orsoft.quizzer_api.domain.models.attempt;

import com.orsoft.quizzer_api.domain.models.answer.Answer;
import com.orsoft.quizzer_api.domain.models.question.Question;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "attempt_answers")
public class AttemptAnswer {
  private UUID id = UUID.randomUUID();
  private String value;

  private Attempt attempt;
  private Question question;
  private Answer answer;

  @Id
  @GeneratedValue
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Column
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @ManyToOne
  @JoinColumn(nullable = false)
  public Attempt getAttempt() {
    return attempt;
  }

  public void setAttempt(Attempt attempt) {
    this.attempt = attempt;
  }

  @ManyToOne
  @JoinColumn(nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  public Answer getAnswer() {
    return answer;
  }

  public void setAnswer(Answer answer) {
    this.answer = answer;
  }
}
