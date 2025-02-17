package com.kamilsmolarek.autofix.workshop.mapper;

import com.kamilsmolarek.autofix.workshop.entity.WorkshopImageEntity;
import com.kamilsmolarek.autofix.workshop.model.WorkshopImage;
import org.springframework.stereotype.Component;

@Component
public class WorkshopImageMapper {

    public WorkshopImageEntity toEntity(WorkshopImage workshopImage) {
        if (workshopImage == null) {
            return null;
        }
        return WorkshopImageEntity.builder()
                .id(workshopImage.getId())
                .workshopId(workshopImage.getWorkshopId())
                .imagePath(workshopImage.getImagePath())
                .fileName(workshopImage.getFileName())
                .build();
    }

    public WorkshopImage toDomain(WorkshopImageEntity entity) {
        if (entity == null) {
            return null;
        }
        return WorkshopImage.builder()
                .id(entity.getId())
                .workshopId(entity.getWorkshopId())
                .imagePath(entity.getImagePath())
                .fileName(entity.getFileName())
                .build();
    }
}

