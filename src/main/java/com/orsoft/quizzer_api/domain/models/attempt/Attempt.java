package com.orsoft.quizzer_api.domain.models.attempt;

import com.orsoft.quizzer_api.domain.models.quiz.Quiz;
import com.orsoft.quizzer_api.domain.models.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "attempts")
@EntityListeners(AuditingEntityListener.class)
public class Attempt {
  private UUID id = UUID.randomUUID();
  private Date createdAt;
  private Date updatedAt;

  private Quiz quiz;
  private User user;
  private Set<AttemptAnswer> answers = new HashSet<>();

  @Id
  @GeneratedValue
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Column(
    name = "created_at",
    nullable = false,
    updatable = false
  )
  @CreatedDate
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Column(
    name = "updated_at",
    nullable = false
  )
  @LastModifiedDate
  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @ManyToOne
  @JoinColumn(nullable = false)
  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  @ManyToOne
  @JoinColumn(nullable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @OneToMany(
    mappedBy = "attempt",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  public Set<AttemptAnswer> getAnswers() {
    return answers;
  }

  public void setAnswers(Set<AttemptAnswer> answers) {
    answers.forEach(answer -> answer.setAttempt(this));

    this.answers = answers;
  }
}
