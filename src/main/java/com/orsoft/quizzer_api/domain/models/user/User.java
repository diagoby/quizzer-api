package com.orsoft.quizzer_api.domain.models.user;

import com.orsoft.quizzer_api.domain.models.attempt.Attempt;
import com.orsoft.quizzer_api.domain.models.quiz.Quiz;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
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

  private Set<Quiz> quizzes = new HashSet<>();
  private Set<Attempt> attempts = new HashSet<>();

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

  @OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  public Set<Attempt> getAttempts() {
    return attempts;
  }

  public void setAttempts(Set<Attempt> attempts) {
    this.attempts = attempts;
  }
}
