package com.dealership.backend.validation;

import com.dealership.backend.dto.VehicleInput;
import com.dealership.backend.enums.FuelType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidVehicleEngineValidator implements ConstraintValidator<ValidVehicleEngine, VehicleInput> {

    @Override
    public void initialize(ValidVehicleEngine constraintAnnotation) {}

    @Override
    public boolean isValid(VehicleInput vehicle, ConstraintValidatorContext context) {
        if (vehicle == null || vehicle.getFuel() == null) {
            return true;
        }

        try {
            FuelType fuelType = FuelType.fromString(vehicle.getFuel());
            
            context.disableDefaultConstraintViolation();

            boolean valid = true;

            if (fuelType.requiresCm3() && (vehicle.getCm3() == null || vehicle.getCm3() <= 0)) {
                context.buildConstraintViolationWithTemplate(
                    "cm3 is required and must be positive for fuel type: " + fuelType.getValue())
                    .addPropertyNode("cm3")
                    .addConstraintViolation();
                valid = false;
            }

            if (fuelType.requiresKwh() && (vehicle.getKwh() == null || vehicle.getKwh() <= 0)) {
                context.buildConstraintViolationWithTemplate(
                    "kwh is required and must be positive for fuel type: " + fuelType.getValue())
                    .addPropertyNode("kwh")
                    .addConstraintViolation();
                valid = false;
            }

            if (fuelType.isElectricOnly() && vehicle.getCm3() != null && vehicle.getCm3() > 0) {
                context.buildConstraintViolationWithTemplate(
                    "cm3 should not be specified for electric vehicles")
                    .addPropertyNode("cm3")
                    .addConstraintViolation();
                valid = false;
            }

            if (!fuelType.requiresKwh() && vehicle.getKwh() != null && vehicle.getKwh() > 0) {
                context.buildConstraintViolationWithTemplate(
                    "kwh should not be specified for fuel type: " + fuelType.getValue())
                    .addPropertyNode("kwh")
                    .addConstraintViolation();
                valid = false;
            }

            return valid;

        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}