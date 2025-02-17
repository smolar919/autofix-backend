package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.workshop.model.WorkshopImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkshopImageService {
    WorkshopImage uploadImage(String workshopId, MultipartFile file);
    List<WorkshopImage> getImagesByWorkshopId(String workshopId);
    byte[] downloadImage(String imageId);
}
