package com.orsoft.quizzer_api.domain.models.quiz;

import com.orsoft.quizzer_api.domain.models.attempt.Attempt;
import com.orsoft.quizzer_api.domain.models.question.Question;
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
@Table(name = "quizzes")
@EntityListeners(AuditingEntityListener.class)
public class Quiz {
  private UUID id = UUID.randomUUID();
  private String title;
  private String description;
  private Date createdAt;
  private Date updatedAt;

  private User user;
  private Set<Question> questions = new HashSet<>();
  private Set<Attempt> attempts = new HashSet<>();

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

  @Column
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @OneToMany(
    mappedBy = "quiz",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  public Set<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<Question> questions) {
    questions.forEach(question -> question.setQuiz(this));

    this.questions = questions;
  }

  @OneToMany(
    mappedBy = "quiz",
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
