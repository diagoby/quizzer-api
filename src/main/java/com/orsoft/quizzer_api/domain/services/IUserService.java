package com.orsoft.quizzer_api.domain.services;

import com.orsoft.quizzer_api.domain.contracts.dto.LoginUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.ReadUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.RegisterUserDTO;

import java.util.Optional;

public interface IUserService {
  void register(RegisterUserDTO userDto);
  Optional<ReadUserDTO> login(LoginUserDTO userDto);
}
