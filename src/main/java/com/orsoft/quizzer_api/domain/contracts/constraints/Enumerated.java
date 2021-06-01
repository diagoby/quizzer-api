package com.orsoft.quizzer_api.domain.contracts.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Constraint(validatedBy = EnumeratedValidator.class)
public @interface Enumerated {
  Class<? extends Enum<?>> enumClass();
  Class<? extends Payload>[] payload() default {};
  Class<?>[] groups() default {};
  String message() default "must be any of enum {enumClass}";
}
