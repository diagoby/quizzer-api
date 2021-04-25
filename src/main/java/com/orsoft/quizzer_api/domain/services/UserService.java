package com.orsoft.quizzer_api.domain.services;

import com.orsoft.quizzer_api.domain.contracts.dto.LoginUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.RegisterUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.ReadUserDTO;
import com.orsoft.quizzer_api.domain.contracts.mappers.UserMapper;
import com.orsoft.quizzer_api.domain.errors.UserAlreadyRegisteredError;
import com.orsoft.quizzer_api.domain.models.User;
import com.orsoft.quizzer_api.domain.utils.Result;
import com.orsoft.quizzer_api.infrastructure.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private SecurityService securityService;

  @Override
  public Result<Object, String> register(RegisterUserDTO userDto) {
    if(userRepository.existsByEmail(userDto.email)) {
      return Result.error(new UserAlreadyRegisteredError().getMessage());
    }

    User user = this.userMapper.toEntity(userDto);
    user.setPassword(securityService.encodePassword(user.getPassword()));

    userRepository.save(user);

    return Result.empty();
  }

  @Override
  public Result<ReadUserDTO, String> login(LoginUserDTO userDto) {
    Optional<ReadUserDTO> readUserDto = userRepository.findByEmail(userDto.email)
      .filter(u -> securityService.matchPasswordHash(userDto.password, u.getPassword()))
      .map(this.userMapper::toDTO);

    return Result.ofOptional(readUserDto, "Wrong email or password");
  }
}
