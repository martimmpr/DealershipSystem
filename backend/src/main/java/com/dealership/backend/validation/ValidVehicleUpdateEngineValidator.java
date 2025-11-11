package com.dealership.backend.validation;

import com.dealership.backend.dto.VehicleUpdateInput;
import com.dealership.backend.enums.FuelType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidVehicleUpdateEngineValidator implements ConstraintValidator<ValidVehicleEngine, VehicleUpdateInput> {

    @Override
    public void initialize(ValidVehicleEngine constraintAnnotation) {}

    @Override
    public boolean isValid(VehicleUpdateInput vehicle, ConstraintValidatorContext context) {
        if (vehicle == null || vehicle.getFuel() == null) {
            return true;
        }

        try {
            FuelType fuelType = FuelType.fromString(vehicle.getFuel());
            
            context.disableDefaultConstraintViolation();

            boolean valid = true;

            if (vehicle.getCm3() != null) {
                if (fuelType.requiresCm3() && vehicle.getCm3() <= 0) {
                    context.buildConstraintViolationWithTemplate(
                        "cm3 must be positive for fuel type: " + fuelType.getValue())
                        .addPropertyNode("cm3")
                        .addConstraintViolation();
                    valid = false;
                }
                
                if (fuelType.isElectricOnly() && vehicle.getCm3() > 0) {
                    context.buildConstraintViolationWithTemplate(
                        "cm3 should not be specified for electric vehicles")
                        .addPropertyNode("cm3")
                        .addConstraintViolation();
                    valid = false;
                }
            }

            if (vehicle.getKwh() != null) {
                if (fuelType.requiresKwh() && vehicle.getKwh() <= 0) {
                    context.buildConstraintViolationWithTemplate(
                        "kwh must be positive for fuel type: " + fuelType.getValue())
                        .addPropertyNode("kwh")
                        .addConstraintViolation();
                    valid = false;
                }
                
                if (!fuelType.requiresKwh() && vehicle.getKwh() > 0) {
                    context.buildConstraintViolationWithTemplate(
                        "kwh should not be specified for fuel type: " + fuelType.getValue())
                        .addPropertyNode("kwh")
                        .addConstraintViolation();
                    valid = false;
                }
            }

            return valid;

        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}