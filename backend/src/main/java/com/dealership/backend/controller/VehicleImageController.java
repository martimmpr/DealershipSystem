package com.dealership.backend.controller;

import com.dealership.backend.dto.ImageUploadResponse;
import com.dealership.backend.dto.ImageDeleteResponse;
import com.dealership.backend.service.ImageService;
import com.dealership.backend.service.VehicleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleImageController {

    private final ImageService imageService;
    private final VehicleService vehicleService;

    public VehicleImageController(ImageService imageService, VehicleService vehicleService) {
        this.imageService = imageService;
        this.vehicleService = vehicleService;
    }

    @PostMapping("/{vehicleId}/images")
    public ResponseEntity<ImageUploadResponse> uploadImage(@PathVariable UUID vehicleId, @RequestParam("image") MultipartFile file) {
        
        try {
            if (vehicleService.getVehicleById(vehicleId).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(new ImageUploadResponse(vehicleId, 0, null, "No file provided!"));
            }

            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body(new ImageUploadResponse(vehicleId, 0, null, "File must be an image!"));
            }

            String imageName = imageService.addVehicleImage(vehicleId, file);
            int imageNumber = extractImageNumber(imageName);

            return ResponseEntity.ok(new ImageUploadResponse(
                vehicleId, imageNumber, imageName, "Image uploaded successfully!"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ImageUploadResponse(vehicleId, 0, null, "Error uploading image: " + e.getMessage() + "!"));
        }
    }

    @GetMapping("/{vehicleId}/images")
    public ResponseEntity<List<String>> getVehicleImages(@PathVariable UUID vehicleId) {
        try {
            if (vehicleService.getVehicleById(vehicleId).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            List<String> images = imageService.getVehicleImages(vehicleId);
            return ResponseEntity.ok(images);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{vehicleId}/images/{imageNumber}")
    public ResponseEntity<byte[]> getImage(@PathVariable UUID vehicleId, @PathVariable int imageNumber) {
        try {
            if (vehicleService.getVehicleById(vehicleId).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            InputStream imageStream = imageService.getImageStream(vehicleId, imageNumber);
            byte[] imageBytes = imageStream.readAllBytes();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(imageBytes.length);
            
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{vehicleId}/images/{imageNumber}")
    public ResponseEntity<ImageDeleteResponse> deleteImage(@PathVariable UUID vehicleId, @PathVariable int imageNumber) {
        try {
            if (vehicleService.getVehicleById(vehicleId).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            boolean deleted = imageService.deleteVehicleImage(vehicleId, imageNumber);
            
            if (deleted) {
                return ResponseEntity.ok(new ImageDeleteResponse(vehicleId, imageNumber, true, "Image deleted successfully."));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ImageDeleteResponse(vehicleId, imageNumber, false, "Image not found!"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ImageDeleteResponse(vehicleId, imageNumber, false, "Error deleting image: " + e.getMessage() + "!"));
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