package com.orsoft.quizzer_api.domain.utils;

public class Validation {
  public static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

  public static boolean isValidUuidString(String raw) {
    return raw.matches(UUID_PATTERN);
  }
}
