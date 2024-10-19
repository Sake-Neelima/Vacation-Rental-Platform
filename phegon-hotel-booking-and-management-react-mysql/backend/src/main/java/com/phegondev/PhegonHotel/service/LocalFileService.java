package com.phegondev.PhegonHotel.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phegondev.PhegonHotel.exception.OurException;


@Service
public class LocalFileService {

    private final String localFolderPath = "H:\\hotel-booking-and-management\\phegon-hotel-booking-and-management-react-mysql\\Images"; // Specify your local folder path here

    public String saveImageLocally(MultipartFile photo) {
        String localFilePath = null;

        try {
            String originalFilename = photo.getOriginalFilename();
            Path targetPath = Paths.get(localFolderPath, originalFilename);

            // Ensure the local folder exists
            Files.createDirectories(targetPath.getParent());

            // Save the file locally
            photo.transferTo(targetPath.toFile());
            localFilePath = targetPath.toString();

            return localFilePath;

        } catch (IOException e) {
            e.printStackTrace();
            throw new OurException("Unable to save image to local folder: " + e.getMessage());
        }
    }
}

















