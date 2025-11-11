package com.dealership.backend.enums;

public enum FuelType {
    GASOLINE("gasoline"),
    DIESEL("diesel"),
    ELECTRIC("electric"),
    GASOLINE_HYBRID("gasoline_hybrid"),
    DIESEL_HYBRID("diesel_hybrid"),
    HYBRID_PLUGIN("hybrid_plugin");

    private final String value;

    FuelType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FuelType fromString(String text) {
        for (FuelType fuelType : FuelType.values()) {
            if (fuelType.value.equalsIgnoreCase(text)) {
                return fuelType;
            }
        }
        throw new IllegalArgumentException("Invalid fuel type: " + text + ". Valid types are: gasoline, diesel, electric, gasoline_hybrid, diesel_hybrid, hybrid_plugin");
    }

    public boolean requiresCm3() {
        return this == GASOLINE || this == DIESEL || this == GASOLINE_HYBRID || this == DIESEL_HYBRID || this == HYBRID_PLUGIN;
    }

    public boolean requiresKwh() {
        return this == ELECTRIC || this == GASOLINE_HYBRID || this == DIESEL_HYBRID || this == HYBRID_PLUGIN;
    }

    public boolean isElectricOnly() {
        return this == ELECTRIC;
    }
}