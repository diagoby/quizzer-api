package com.orsoft.quizzer_api.domain.contracts.dto.user;

public class ReadUserDTO {
  public String id;
  public String email;
  public String fullName;

  public ReadUserDTO(String id, String email, String fullName) {
    this.id = id;
    this.email = email;
    this.fullName = fullName;
  }
}
