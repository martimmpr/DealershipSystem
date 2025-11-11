package com.dealership.backend.enums;

public enum VehicleSegment {
    CABRIO("cabrio"),
    ESTATE("estate"),
    CITY("city"),
    COUPE("coupe"),
    MINIVAN("minivan"),
    SEDAN("sedan"),
    SUV("suv"),
    OFFROAD("offroad"),
    UTILITARIAN("utilitarian");

    private final String value;

    VehicleSegment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static VehicleSegment fromString(String text) {
        for (VehicleSegment segment : VehicleSegment.values()) {
            if (segment.value.equalsIgnoreCase(text)) {
                return segment;
            }
        }
        
        throw new IllegalArgumentException("Invalid vehicle segment: " + text + ". Valid segments are: cabrio, estate, city, coupe, minivan, sedan, suv, offroad, utilitarian");
    }
}