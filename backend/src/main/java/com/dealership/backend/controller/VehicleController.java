package com.dealership.backend.controller;

import com.dealership.backend.entity.Vehicle;
import com.dealership.backend.dto.VehicleInput;
import com.dealership.backend.dto.VehicleUpdateInput;
import com.dealership.backend.service.VehicleService;
import com.dealership.backend.service.ImageService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class VehicleController {
    private final VehicleService vehicleService;
    private final ImageService imageService;

    public VehicleController(VehicleService vehicleService, ImageService imageService) {
        this.vehicleService = vehicleService;
        this.imageService = imageService;
    }

    @QueryMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @QueryMapping
    public List<Vehicle> getAllAvailableVehicles() {
        return vehicleService.getAllAvailableVehicles();
    }

    @QueryMapping
    public Vehicle getVehicleById(@Argument UUID id) {
        return vehicleService.getVehicleById(id).orElse(null);
    }

    @MutationMapping
    public Vehicle createVehicle(@Argument("input") @Valid VehicleInput input) {
        return vehicleService.createVehicle(input);
    }

    @MutationMapping
    public Vehicle updateVehicle(@Argument("input") @Valid VehicleUpdateInput input) {
        return vehicleService.updateVehicle(input).orElse(null);
    }

    @MutationMapping
    public Boolean deleteVehicle(@Argument UUID id, @Argument Boolean hardDelete) {
        boolean performHardDelete = hardDelete != null && hardDelete;
        
        return vehicleService.deleteVehicle(id, !performHardDelete);
    }

    @QueryMapping
    public List<String> getVehicleImages(@Argument UUID vehicleId) {
        return imageService.getVehicleImages(vehicleId);
    }

    @SchemaMapping(typeName = "Vehicle", field = "imageUrls")
    public List<String> vehicleImageUrls(Vehicle vehicle) {
        try {
            List<String> imageNames = imageService.getVehicleImages(vehicle.getId());
            return imageNames.stream()
                .map(imageName -> "/api/vehicles/" + vehicle.getId() + "/images/" + extractImageNumber(imageName))
                .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }

    private int extractImageNumber(String objectName) {
        String[] parts = objectName.split("/");
        if (parts.length >= 3) {
            String filename = parts[2];
            String numberPart = filename.substring(0, filename.lastIndexOf('.'));
            return Integer.parseInt(numberPart);
        }
        return 0;
    }
}