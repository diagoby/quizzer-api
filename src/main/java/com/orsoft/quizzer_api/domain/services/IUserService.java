package com.orsoft.quizzer_api.domain.services;

import com.orsoft.quizzer_api.domain.models.User;
import com.orsoft.quizzer_api.domain.contracts.dto.CreateUserDTO;

public interface IUserService {
  User register(CreateUserDTO userDto);
}
