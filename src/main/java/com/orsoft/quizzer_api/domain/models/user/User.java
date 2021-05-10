package com.orsoft.quizzer_api.domain.models.user;

import com.orsoft.quizzer_api.domain.models.quiz.Quiz;

import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

@Entity()
@Table(name = "users")
public class User {
  private UUID id = UUID.randomUUID();
  private String email;
  private String fullName;
  private String password;

  private Set<Quiz> quizzes;

  @Id
  @GeneratedValue
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Column()
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name="full_name")
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Column
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @OneToMany(mappedBy = "user")
  public Set<Quiz> getQuizzes() {
    return quizzes;
  }

  public void setQuizzes(Set<Quiz> quizzes) {
    this.quizzes = quizzes;
  }
}
