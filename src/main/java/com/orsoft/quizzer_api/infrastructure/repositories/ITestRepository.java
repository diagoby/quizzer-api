package com.orsoft.quizzer_api.infrastructure.repositories;

import com.orsoft.quizzer_api.domain.models.Test;

import org.springframework.data.repository.CrudRepository;

public interface ITestRepository extends CrudRepository<Test, Long> {}
