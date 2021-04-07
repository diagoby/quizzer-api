package com.orsoft.quizzer_api.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import com.orsoft.quizzer_api.domain.models.User;

import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, UUID> {
  Optional<User> findByEmail(String email);
}
