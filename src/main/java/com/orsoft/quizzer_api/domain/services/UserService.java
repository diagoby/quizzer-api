package com.orsoft.quizzer_api.domain.services;

import com.orsoft.quizzer_api.domain.contracts.dto.LoginUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.RegisterUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.ReadUserDTO;
import com.orsoft.quizzer_api.domain.contracts.mappers.UserMapper;
import com.orsoft.quizzer_api.domain.errors.UserAlreadyRegisteredError;
import com.orsoft.quizzer_api.domain.models.User;
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
  public Optional<UserAlreadyRegisteredError> register(RegisterUserDTO userDto) {
    if(userRepository.existsByEmail(userDto.email)) {
      return Optional.of(new UserAlreadyRegisteredError());
    }

    User user = this.userMapper.toEntity(userDto);
    user.setPassword(securityService.encodePassword(user.getPassword()));

    userRepository.save(user);

    return Optional.empty();
  }

  @Override
  public Optional<ReadUserDTO> login(LoginUserDTO userDto) {
    return userRepository.findByEmail(userDto.email)
      .filter(u -> securityService.matchPasswordHash(userDto.password, u.getPassword()))
      .map(this.userMapper::toDTO);
  }
}
