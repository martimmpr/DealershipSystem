package com.dealership.backend.service;

import io.minio.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public ImageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    private void ensureBucketExists() {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating bucket: " + e.getMessage(), e);
        }
    }

    public String addVehicleImage(UUID vehicleId, MultipartFile file) {
        ensureBucketExists();
        
        try {
            int nextImageNumber = getNextImageNumber(vehicleId);
            String objectName = generateObjectName(vehicleId, nextImageNumber);
            String contentType = file.getContentType();
            
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(contentType)
                    .build()
            );
            
            return objectName;
            
        } catch (Exception e) {
            throw new RuntimeException("Error uploading image: " + e.getMessage(), e);
        }
    }

    private int getNextImageNumber(UUID vehicleId) {
        List<String> existingImages = getVehicleImageNames(vehicleId);
        return existingImages.size() + 1;
    }
    
    public boolean deleteVehicleImage(UUID vehicleId, int imageNumber) {
        ensureBucketExists();
        
        try {
            String objectNameToDelete = generateObjectName(vehicleId, imageNumber);
            
            try {
                minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectNameToDelete)
                    .build());
            } catch (Exception e) {
                return false;
            }
            
            minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectNameToDelete)
                .build());
            
            renumberVehicleImages(vehicleId, imageNumber);
            
            return true;
            
        } catch (Exception e) {
            throw new RuntimeException("Error deleting image: " + e.getMessage(), e);
        }
    }

    private void renumberVehicleImages(UUID vehicleId, int deletedImageNumber) {
        try {
            List<String> allImages = getVehicleImageNames(vehicleId);
            List<String> imagesToRename = new ArrayList<>();
            
            for (String imageName : allImages) {
                int imageNumber = extractImageNumber(imageName);
                if (imageNumber > deletedImageNumber) {
                    imagesToRename.add(imageName);
                }
            }
            
            for (String oldImageName : imagesToRename) {
                int oldNumber = extractImageNumber(oldImageName);
                int newNumber = oldNumber - 1;
                String newImageName = generateObjectName(vehicleId, newNumber);
                
                minioClient.copyObject(CopyObjectArgs.builder()
                    .bucket(bucketName)
                    .object(newImageName)
                    .source(CopySource.builder()
                        .bucket(bucketName)
                        .object(oldImageName)
                        .build())
                    .build());
                
                minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(oldImageName)
                    .build());
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error renumbering images: " + e.getMessage(), e);
        }
    }

    public void deleteAllVehicleImages(UUID vehicleId) {
        ensureBucketExists();
        
        try {
            List<String> vehicleImages = getVehicleImageNames(vehicleId);
            for (String imageName : vehicleImages) {
                minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(imageName)
                    .build());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting vehicle images: " + e.getMessage(), e);
        }
    }

    public List<String> getVehicleImages(UUID vehicleId) {
        ensureBucketExists();
        return getVehicleImageNames(vehicleId);
    }

    public InputStream getImageStream(UUID vehicleId, int imageNumber) {
        ensureBucketExists();
        
        try {
            String objectName = generateObjectName(vehicleId, imageNumber);
            return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
        } catch (Exception e) {
            throw new RuntimeException("Error getting image: " + e.getMessage(), e);
        }
    }

    // ========== UTILITY FUNCTIONS ==========
    
    private List<String> getVehicleImageNames(UUID vehicleId) {
        try {
            List<String> imageNames = new ArrayList<>();
            String prefix = "vehicles/" + vehicleId + "/";
            
            Iterable<Result<Item>> objects = minioClient.listObjects(
                ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .prefix(prefix)
                    .build()
            );
            
            for (Result<Item> result : objects) {
                Item item = result.get();
                imageNames.add(item.objectName());
            }
            
            imageNames.sort((a, b) -> {
                int numA = extractImageNumber(a);
                int numB = extractImageNumber(b);
                return Integer.compare(numA, numB);
            });
            
            return imageNames;
            
        } catch (Exception e) {
            throw new RuntimeException("Error listing vehicle images: " + e.getMessage(), e);
        }
    }

    private String generateObjectName(UUID vehicleId, int imageNumber) {
        return "vehicles/" + vehicleId + "/" + imageNumber + ".jpg";
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