package com.receitas.api.api.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ FIELD, METHOD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedContentTypesValidator.class)
@Documented
public @interface AllowedContentTypes {

	String message() default "{AllowedContentTypesValidator.message}";

	String[] value() default {};

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
