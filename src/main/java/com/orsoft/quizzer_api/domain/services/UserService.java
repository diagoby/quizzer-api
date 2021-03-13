package com.orsoft.quizzer_api.domain.services;

import com.orsoft.quizzer_api.domain.contracts.dto.CreateUserDTO;
import com.orsoft.quizzer_api.domain.contracts.mappers.UserMapper;
import com.orsoft.quizzer_api.domain.models.User;
import com.orsoft.quizzer_api.infrastructure.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @Override
  public User register(CreateUserDTO userDto) {
    return userRepository.save(this.userMapper.toEntity(userDto));
  }
}
