package com.orsoft.quizzer_api.domain.contracts.dto;

public class CreateUserDTO {
  public String fullName;
  public String email;
  public String password;

  public CreateUserDTO(String fullName, String email, String password) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
  }
}
