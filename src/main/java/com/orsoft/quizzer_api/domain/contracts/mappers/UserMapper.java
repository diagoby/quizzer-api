package com.orsoft.quizzer_api.domain.contracts.mappers;

import com.orsoft.quizzer_api.domain.models.User;

import org.springframework.stereotype.Component;

import com.orsoft.quizzer_api.domain.contracts.dto.CreateUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.ReadUserDTO;

@Component
public class UserMapper implements IEntityMapper<User, CreateUserDTO, ReadUserDTO> {

  @Override
  public User toEntity(CreateUserDTO dto) {
    User user = new User();

    user.setFullName(dto.fullName);
    user.setEmail(dto.email);
    user.setPassword(dto.password);

    return user;
  }

  @Override
  public ReadUserDTO toDTO(User entity) {
    // TODO Auto-generated method stub
    return null;
  }

}
