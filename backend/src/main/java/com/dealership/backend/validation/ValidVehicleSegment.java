package com.dealership.backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidVehicleSegmentValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVehicleSegment {
    String message() default "Invalid vehicle segment. Valid segments are: cabrio, estate, city, coupe, minivan, sedan, suv, offroad, utilitarian";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}