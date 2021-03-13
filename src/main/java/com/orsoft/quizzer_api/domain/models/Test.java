package com.orsoft.quizzer_api.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tests")
public class Test {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String question;

  @Column
  private String answer;

  @Column
  @Enumerated(EnumType.STRING)
  private Type type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}

enum Type {
  SINGLE, MULTIPLE, TRUE_FALSE, CUSTOM
}
