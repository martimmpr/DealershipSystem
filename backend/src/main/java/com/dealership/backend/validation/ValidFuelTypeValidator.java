package com.dealership.backend.validation;

import com.dealership.backend.enums.FuelType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidFuelTypeValidator implements ConstraintValidator<ValidFuelType, String> {
    @Override
    public void initialize(ValidFuelType constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        try {
            FuelType.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}