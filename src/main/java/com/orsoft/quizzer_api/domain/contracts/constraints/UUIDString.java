package com.orsoft.quizzer_api.domain.contracts.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UUIDValidator.class)
public @interface UUIDString {
  Class<? extends Payload>[] payload() default {};
  boolean allowNull() default false;
  Class<?>[] groups() default {};
  String message() default "must be a valid UUID string";
}
