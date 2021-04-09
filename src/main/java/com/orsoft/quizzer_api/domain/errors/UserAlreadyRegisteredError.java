package com.orsoft.quizzer_api.domain.errors;

public class UserAlreadyRegisteredError {
  private final String message = "User already registered";

  public String getMessage() {
    return message;
  }
}
