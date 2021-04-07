package com.orsoft.quizzer_api.domain.contracts.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginUserDTO {
  @NotNull
  @Email
  public String email;

  @NotBlank
  public String password;

  public LoginUserDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
