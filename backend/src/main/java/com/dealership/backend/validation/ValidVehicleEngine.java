package com.dealership.backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidVehicleEngineValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVehicleEngine {
    String message() default "Invalid engine configuration for fuel type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}