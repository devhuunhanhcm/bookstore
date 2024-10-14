package com.indentity_oauth2.security.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePhoneValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface  UniquePhone {
    String message() default "Phone number is existed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
