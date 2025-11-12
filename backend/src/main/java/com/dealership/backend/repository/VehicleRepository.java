package com.dealership.backend.repository;

import com.dealership.backend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    List<Vehicle> findByDeletedFalseAndSoldFalse();
    List<Vehicle> findByDeletedFalse();
}