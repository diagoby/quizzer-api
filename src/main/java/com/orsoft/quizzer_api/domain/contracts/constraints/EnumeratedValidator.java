package com.orsoft.quizzer_api.domain.contracts.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumeratedValidator implements ConstraintValidator<Enumerated, String> {
  private Set<String> ALLOWED_NAMES;

  @Override
  public void initialize(Enumerated annotation) {
    ALLOWED_NAMES = getAllowedNames(annotation.enumClass());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || ALLOWED_NAMES.contains(value);
  }

  private Set<String> getAllowedNames(Class<? extends Enum<?>> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants())
      .map(Enum::name)
      .collect(Collectors.toSet());
  }
}
