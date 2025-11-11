package com.dealership.backend.dto;

import java.util.UUID;

public class ImageUploadResponse {
    private UUID vehicleId;
    private int imageNumber;
    private String imageName;
    private String message;

    public ImageUploadResponse() {}

    public ImageUploadResponse(UUID vehicleId, int imageNumber, String imageName, String message) {
        this.vehicleId = vehicleId;
        this.imageNumber = imageNumber;
        this.imageName = imageName;
        this.message = message;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(UUID vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}