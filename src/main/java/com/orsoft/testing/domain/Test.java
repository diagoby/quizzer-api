package com.orsoft.testing_api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "tests")
public class Test {
  @Id()
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column
  private String question;

  @Column
  @Enumerated(EnumType.STRING)
  private Type type;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
}

enum Type {
  SINGLE, MULTIPLE, TRUE_FALSE, CUSTOM
}
