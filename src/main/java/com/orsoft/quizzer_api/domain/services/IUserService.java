package com.orsoft.quizzer_api.domain.services;

import com.orsoft.quizzer_api.domain.contracts.dto.LoginUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.ReadUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.RegisterUserDTO;
import com.orsoft.quizzer_api.domain.errors.UserAlreadyRegisteredError;

import java.util.Optional;

public interface IUserService {
  Optional<UserAlreadyRegisteredError> register(RegisterUserDTO userDto);
  Optional<ReadUserDTO> login(LoginUserDTO userDto);
}
