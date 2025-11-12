package com.dealership.backend.dto;

import java.util.UUID;
import com.dealership.backend.validation.ValidFuelType;
import com.dealership.backend.validation.ValidVehicleSegment;
import com.dealership.backend.validation.ValidVehicleUpdateEngine;
import com.dealership.backend.validation.ValidTransmission;

@ValidVehicleUpdateEngine
public class VehicleUpdateInput {
    private UUID id;
    private String brand;
    private String model;
    
    @ValidVehicleSegment
    private String segment;
    private Double price;
    private Integer kms;
    private Integer month;
    private Integer year;
    
    @ValidFuelType
    private String fuel;
    private Integer cm3;
    private Double kwh;
    private Integer hp;
    
    @ValidTransmission
    private String transmission;
    private String consumption;
    private Boolean sold;
    private Boolean deleted;

    public VehicleUpdateInput() {}

    public VehicleUpdateInput(UUID id, String brand, String model, String segment, Double price, Integer kms, Integer month, Integer year, String fuel, Integer cm3, Double kwh, Integer hp, String transmission, String consumption, Boolean sold, Boolean deleted) {
        this.id = id;
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
        this.transmission = transmission;
        this.consumption = consumption;
        this.sold = sold;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}