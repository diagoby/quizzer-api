package com.orsoft.quizzer_api.domain.services.user;

import com.orsoft.quizzer_api.domain.contracts.dto.user.LoginUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.user.ReadUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.user.RegisterUserDTO;
import com.orsoft.quizzer_api.domain.utils.Result;

public interface IUserService {
  Result<Object, String> register(RegisterUserDTO userDto);
  Result<ReadUserDTO, String> login(LoginUserDTO userDto);
}
