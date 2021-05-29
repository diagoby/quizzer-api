package com.orsoft.quizzer_api.infrastructure.repositories;

import com.orsoft.quizzer_api.domain.models.attempt.Attempt;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IAttemptRepository extends CrudRepository<Attempt, UUID> { }
