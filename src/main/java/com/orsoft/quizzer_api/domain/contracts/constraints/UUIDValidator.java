package com.orsoft.quizzer_api.domain.contracts.constraints;

import com.orsoft.quizzer_api.domain.utils.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UUIDValidator implements ConstraintValidator<UUIDString, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return Validation.isValidUuidString(value);
  }
}
