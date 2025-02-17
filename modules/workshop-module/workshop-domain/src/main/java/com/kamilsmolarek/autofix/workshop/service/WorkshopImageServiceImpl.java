package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.workshop.model.WorkshopImage;
import com.kamilsmolarek.autofix.workshop.repository.WorkshopImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class WorkshopImageServiceImpl implements WorkshopImageService {

    private final WorkshopImageRepository workshopImageRepository;

    @Value("${app.workshop.images.storage-directory}")
    private String storageDirectory;

    public WorkshopImageServiceImpl(WorkshopImageRepository workshopImageRepository) {
        this.workshopImageRepository = workshopImageRepository;
    }

    @Override
    public WorkshopImage uploadImage(String workshopId, MultipartFile file) {
        try {
            String imageRecordId = UUID.randomUUID().toString();
            String originalFileName = file.getOriginalFilename();

            Path imageFolder = Paths.get(storageDirectory, imageRecordId);
            Files.createDirectories(imageFolder);

            assert originalFileName != null;
            Path imagePath = imageFolder.resolve(originalFileName);
            Files.write(imagePath, file.getBytes());

            String storedRelativePath = imageRecordId + "/" + originalFileName;

            WorkshopImage workshopImage = WorkshopImage.builder()
                    .id(imageRecordId)
                    .workshopId(workshopId)
                    .imagePath(storedRelativePath)
                    .fileName(originalFileName)
                    .build();

            return workshopImageRepository.save(workshopImage);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading image", e);
        }
    }

    @Override
    public List<WorkshopImage> getImagesByWorkshopId(String workshopId) {
        return workshopImageRepository.findByWorkshopId(workshopId);
    }

    @Override
    public byte[] downloadImage(String imageId) {
        WorkshopImage workshopImage = workshopImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        try {
            Path imagePath = Paths.get(storageDirectory, workshopImage.getImagePath());
            return Files.readAllBytes(imagePath);
        } catch (Exception e) {
            throw new RuntimeException("Error downloading image", e);
        }
    }
}