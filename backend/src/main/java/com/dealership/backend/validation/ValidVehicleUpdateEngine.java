package com.dealership.backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidVehicleUpdateEngineValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVehicleUpdateEngine {
    String message() default "Invalid engine configuration for fuel type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}