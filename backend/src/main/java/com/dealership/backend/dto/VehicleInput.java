package com.dealership.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import com.dealership.backend.validation.ValidFuelType;
import com.dealership.backend.validation.ValidVehicleSegment;
import com.dealership.backend.validation.ValidVehicleEngine;

@ValidVehicleEngine
public class VehicleInput {
    @NotBlank(message = "Brand is required!")
    private String brand;
    
    @NotBlank(message = "Model is required!")
    private String model;
    
    @NotBlank(message = "Segment is required!")
    @ValidVehicleSegment
    private String segment;
    
    @NotNull(message = "Price is required!")
    @Positive(message = "Price must be positive!")
    private Double price;
    
    @NotNull(message = "Kilometers is required!")
    @Min(value = 0, message = "Kilometers cannot be negative!")
    private Integer kms;
    
    @NotNull(message = "Year is required!")
    @Min(value = 1900, message = "Year must be valid!")
    private Integer year;
    
    @NotNull(message = "Month is required!")
    @Min(value = 1, message = "Month must be between 1 and 12!")
    @Max(value = 12, message = "Month must be between 1 and 12!")
    private Integer month;
    
    @NotBlank(message = "Fuel type is required!")
    @ValidFuelType
    private String fuel;
    
    private Integer cm3;
    
    private Double kwh;
    
    @NotNull(message = "Horsepower is required!")
    @Positive(message = "Horsepower must be positive!")
    private Integer hp;
    
    @NotBlank(message = "Consumption is required!")
    private String consumption;

    public VehicleInput() {}

    public VehicleInput(String brand, String model, String segment, Double price, Integer kms, Integer month, Integer year, String fuel, Integer cm3, Double kwh, Integer hp, String consumption) {
        this.brand = brand;
        this.model = model;
        this.segment = segment;
        this.price = price;
        this.kms = kms;
        this.month = month;
        this.year = year;
        this.fuel = fuel;
        this.cm3 = cm3;
        this.kwh = kwh;
        this.hp = hp;
        this.consumption = consumption;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
    
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Integer getCm3() {
        return cm3;
    }

    public void setCm3(Integer cm3) {
        this.cm3 = cm3;
    }

    public Double getKwh() {
        return kwh;
    }

    public void setKwh(Double kwh) {
        this.kwh = kwh;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }
}