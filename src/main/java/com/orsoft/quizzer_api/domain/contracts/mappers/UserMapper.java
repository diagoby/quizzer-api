package com.orsoft.quizzer_api.domain.contracts.mappers;

import com.orsoft.quizzer_api.domain.models.user.User;

import org.springframework.stereotype.Component;

import com.orsoft.quizzer_api.domain.contracts.dto.user.RegisterUserDTO;
import com.orsoft.quizzer_api.domain.contracts.dto.user.ReadUserDTO;

@Component
public class UserMapper implements IEntityMapper<User, RegisterUserDTO, ReadUserDTO> {

  @Override
  public User toEntity(RegisterUserDTO dto) {
    User user = new User();

    user.setFullName(dto.fullName);
    user.setEmail(dto.email);
    user.setPassword(dto.password);

    return user;
  }

  @Override
  public ReadUserDTO toDTO(User entity) {
    return new ReadUserDTO(
      entity.getId().toString(),
      entity.getEmail(),
      entity.getFullName()
    );
  }

}
