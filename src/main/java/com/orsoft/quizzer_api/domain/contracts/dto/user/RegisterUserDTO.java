package com.orsoft.quizzer_api.domain.contracts.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterUserDTO {
  @NotBlank
  public String fullName;

  @NotNull
  @Email
  public String email;

  @NotBlank
  public String password;

  public RegisterUserDTO(String fullName, String email, String password) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
  }
}
