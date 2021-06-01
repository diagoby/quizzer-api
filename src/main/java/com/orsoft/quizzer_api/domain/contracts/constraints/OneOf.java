package com.orsoft.quizzer_api.domain.contracts.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE })
@Constraint(validatedBy = OneOfValidator.class)
public @interface OneOf {
  Class<? extends Payload>[] payload() default {};
  Class<?>[] groups() default {};
  String[] peers() default {};
  String message() default "one of {peers} fields must be provided";
}
