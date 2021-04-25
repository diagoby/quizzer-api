package com.orsoft.quizzer_api.domain.services;

import com.orsoft.quizzer_api.domain.contracts.dto.LoginUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.ReadUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.RegisterUserDTO;
import com.orsoft.quizzer_api.domain.errors.UserAlreadyRegisteredError;
import com.orsoft.quizzer_api.domain.utils.Result;

import java.util.Optional;

public interface IUserService {
  Result<Object, String> register(RegisterUserDTO userDto);
  Result<ReadUserDTO, String> login(LoginUserDTO userDto);
}
