package com.orsoft.quizzer_api.domain.utils;

import java.util.Optional;
import java.util.UUID;

public class Convert {
  public static Optional<UUID> stringToUUID(String raw) {
    UUID uuid = null;

    try {
      if(Validation.isValidUuidString(raw)) {
        uuid = UUID.fromString(raw);
      }
    } catch (IllegalArgumentException ex) { }

    return Optional.ofNullable(uuid);
  }
}
