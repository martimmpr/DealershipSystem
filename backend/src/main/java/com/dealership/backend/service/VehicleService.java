package com.dealership.backend.service;

import com.dealership.backend.entity.Vehicle;
import com.dealership.backend.dto.VehicleInput;
import com.dealership.backend.dto.VehicleUpdateInput;
import com.dealership.backend.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ImageService imageService;

    public VehicleService(VehicleRepository vehicleRepository, ImageService imageService) {
        this.vehicleRepository = vehicleRepository;
        this.imageService = imageService;
    }

    public Vehicle createVehicle(VehicleInput input) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(input.getBrand());
        vehicle.setModel(input.getModel());
        vehicle.setSegment(input.getSegment());
        vehicle.setPrice(input.getPrice());
        vehicle.setKms(input.getKms());
        vehicle.setMonth(input.getMonth());
        vehicle.setYear(input.getYear());
        vehicle.setFuel(input.getFuel());
        vehicle.setCm3(input.getCm3());
        vehicle.setKwh(input.getKwh());
        vehicle.setHp(input.getHp());
        vehicle.setConsumption(input.getConsumption());
        
        return vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicleById(UUID id) {
        return vehicleRepository.findById(id);
    }

    public Optional<Vehicle> updateVehicle(VehicleUpdateInput input) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(input.getId());
        
        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();
            
            if (input.getBrand() != null) vehicle.setBrand(input.getBrand());
            if (input.getModel() != null) vehicle.setModel(input.getModel());
            if (input.getSegment() != null) vehicle.setSegment(input.getSegment());
            if (input.getPrice() != null) vehicle.setPrice(input.getPrice());
            if (input.getKms() != null) vehicle.setKms(input.getKms());
            if (input.getMonth() != null) vehicle.setMonth(input.getMonth());
            if (input.getYear() != null) vehicle.setYear(input.getYear());
            if (input.getFuel() != null) vehicle.setFuel(input.getFuel());
            if (input.getCm3() != null) vehicle.setCm3(input.getCm3());
            if (input.getKwh() != null) vehicle.setKwh(input.getKwh());
            if (input.getHp() != null) vehicle.setHp(input.getHp());
            if (input.getConsumption() != null) vehicle.setConsumption(input.getConsumption());
            
            return Optional.of(vehicleRepository.save(vehicle));
        }
        
        return Optional.empty();
    }

    public boolean deleteVehicle(UUID id) {
        if (vehicleRepository.existsById(id)) {
            try {
                imageService.deleteAllVehicleImages(id);
            } catch (Exception e) {
                System.err.println("Error deleting vehicle images: " + e.getMessage());
            }
            
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}