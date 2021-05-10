package com.orsoft.quizzer_api.infrastructure.repositories;

import com.orsoft.quizzer_api.domain.models.quiz.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IQuizRepository extends CrudRepository<Quiz, UUID> { }
