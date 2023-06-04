package com.example.kiemtra.Validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.kiemtra.Validator.ValidUsernameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ TYPE, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidUsernameValidator.class)
@Documented
public @interface ValidUsername {
    String message() default "Username đã tồn tại";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
