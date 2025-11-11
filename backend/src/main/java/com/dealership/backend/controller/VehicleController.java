package com.dealership.backend.controller;

import com.dealership.backend.entity.Vehicle;
import com.dealership.backend.dto.VehicleInput;
import com.dealership.backend.dto.VehicleUpdateInput;
import com.dealership.backend.service.VehicleService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@Controller
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @MutationMapping
    public Vehicle createVehicle(@Argument("input") @Valid VehicleInput input) {
        return vehicleService.createVehicle(input);
    }

    @QueryMapping
    public Vehicle getVehicleById(@Argument UUID id) {
        return vehicleService.getVehicleById(id).orElse(null);
    }

    @QueryMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @MutationMapping
    public Vehicle updateVehicle(@Argument("input") @Valid VehicleUpdateInput input) {
        return vehicleService.updateVehicle(input).orElse(null);
    }

    @MutationMapping
    public Boolean deleteVehicle(@Argument UUID id) {
        return vehicleService.deleteVehicle(id);
    }
}