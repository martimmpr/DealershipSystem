package com.dealership.backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidTransmissionValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTransmission {
    String message() default "Invalid transmission type. Must be one of: MANUAL, AUTOMATIC";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}