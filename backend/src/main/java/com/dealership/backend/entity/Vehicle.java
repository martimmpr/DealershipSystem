package com.dealership.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String brand;
    
    @Column(nullable = false)
    private String model;
    
    @Column(nullable = false)
    private String segment;
    
    @Column(nullable = false)
    private Double price;
    
    @Column(nullable = false)
    private Integer kms;
    
    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private String fuel;
    
    @Column(nullable = true)
    private Integer cm3;
    
    @Column(nullable = true)
    private Double kwh;
    
    @Column(nullable = false)
    private Integer hp;
    
    @Column(nullable = false)
    private String transmission;
    
    @Column(nullable = false)
    private String consumption;
    
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean sold = false;
    
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted = false;
    
    public Vehicle() {}

    public Vehicle(String brand, String model, String segment, Double price, Integer kms, Integer month, Integer year, String fuel, Integer cm3, Double kwh, Integer hp, String transmission, String consumption) {
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
        this.sold = false;
        this.deleted = false;
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