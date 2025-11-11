package com.dealership.backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidFuelTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFuelType {
    String message() default "Invalid fuel type. Valid types are: gasoline, diesel, electric, gasoline_hybrid, diesel_hybrid, hybrid_plugin";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}