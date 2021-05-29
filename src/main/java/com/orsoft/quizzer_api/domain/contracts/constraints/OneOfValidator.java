package com.orsoft.quizzer_api.domain.contracts.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class OneOfValidator implements ConstraintValidator<OneOf, Object> {
  private Set<String> peers;

  @Override
  public void initialize(OneOf annotation) {
    peers = Set.of(annotation.peers());
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    return Arrays.stream(value.getClass().getDeclaredFields())
      .filter(field -> peers.contains(field.getName()))
      .map(field -> {
        try {
          field.setAccessible(true);
          return field.get(value);
        } catch (IllegalAccessException e) {
          return null;
        }
      })
      .filter(Objects::isNull)
      .count() == 1;
  }
}
