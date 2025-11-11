package com.dealership.backend.validation;

import com.dealership.backend.enums.VehicleSegment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidVehicleSegmentValidator implements ConstraintValidator<ValidVehicleSegment, String> {

    @Override
    public void initialize(ValidVehicleSegment constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        try {
            VehicleSegment.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}