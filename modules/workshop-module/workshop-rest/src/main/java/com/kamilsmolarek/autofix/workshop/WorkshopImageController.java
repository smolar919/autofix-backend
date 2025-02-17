package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.workshop.model.WorkshopImage;
import com.kamilsmolarek.autofix.workshop.service.WorkshopImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/workshops")
public class WorkshopImageController {

    private final WorkshopImageService workshopImageService;

    public WorkshopImageController(WorkshopImageService workshopImageService) {
        this.workshopImageService = workshopImageService;
    }

    @PostMapping(value = "/{workshopId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WorkshopImage> uploadImage(@PathVariable String workshopId,
                                                     @RequestParam("file") MultipartFile file) {
        WorkshopImage image = workshopImageService.uploadImage(workshopId, file);
        return new ResponseEntity<>(image, HttpStatus.CREATED);
    }

    @GetMapping("/{workshopId}/images")
    public ResponseEntity<List<WorkshopImage>> getImages(@PathVariable String workshopId) {
        List<WorkshopImage> images = workshopImageService.getImagesByWorkshopId(workshopId);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/images/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String imageId) {
        byte[] imageData = workshopImageService.downloadImage(imageId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }
}
