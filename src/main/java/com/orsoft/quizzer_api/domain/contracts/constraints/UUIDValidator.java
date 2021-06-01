package com.orsoft.quizzer_api.domain.contracts.constraints;

import com.orsoft.quizzer_api.domain.utils.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UUIDValidator implements ConstraintValidator<UUIDString, String> {
  private boolean allowNull;

  @Override
  public void initialize(UUIDString annotation) {
    this.allowNull = annotation.allowNull();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(value == null && allowNull) return true;

    return Validation.isValidUuidString(value);
  }
}
