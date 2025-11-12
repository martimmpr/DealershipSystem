package com.dealership.backend.validation;

import com.dealership.backend.enums.Transmission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidTransmissionValidator implements ConstraintValidator<ValidTransmission, String> {

    @Override
    public void initialize(ValidTransmission constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        
        try {
            Transmission.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}