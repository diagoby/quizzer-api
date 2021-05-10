package com.orsoft.quizzer_api.domain.models.question;

import com.orsoft.quizzer_api.domain.models.answer.Answer;
import com.orsoft.quizzer_api.domain.models.quiz.Quiz;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class Question {
  private UUID id = UUID.randomUUID();
  private String title;
  private QuestionType type;

  private Quiz quiz;
  private Set<Answer> answers;

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

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
    this.type = type;
  }

  @ManyToOne
  @JoinColumn(nullable = false)
  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  @OneToMany(
    mappedBy = "question",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  public Set<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(Set<Answer> answers) {
    this.answers = answers;
  }
}
