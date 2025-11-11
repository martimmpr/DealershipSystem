package com.dealership.backend.dto;

import java.util.UUID;

public class ImageDeleteResponse {
    private UUID vehicleId;
    private int deletedImageNumber;
    private boolean success;
    private String message;

    public ImageDeleteResponse() {}

    public ImageDeleteResponse(UUID vehicleId, int deletedImageNumber, boolean success, String message) {
        this.vehicleId = vehicleId;
        this.deletedImageNumber = deletedImageNumber;
        this.success = success;
        this.message = message;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(UUID vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getDeletedImageNumber() {
        return deletedImageNumber;
    }

    public void setDeletedImageNumber(int deletedImageNumber) {
        this.deletedImageNumber = deletedImageNumber;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}