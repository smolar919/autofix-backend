package com.kamilsmolarek.autofix.workshop.mapper;

import com.kamilsmolarek.autofix.workshop.entity.WorkshopEntity;
import com.kamilsmolarek.autofix.workshop.model.Workshop;

import java.util.stream.Collectors;

public class WorkshopMapper {
    public static Workshop toWorkshop(WorkshopEntity workshopEntity) {
        if (workshopEntity == null) {
            return null;
        }

        return Workshop.builder()
                .id(workshopEntity.getId())
                .name(workshopEntity.getName())
                .address(AddressMapper.toAddress(workshopEntity.getAddress()))
                .employees(null)
                .ownerId(workshopEntity.getOwnerId())
                .isVisible(workshopEntity.isVisible())
                .build();
    }

    public static WorkshopEntity toEntity(Workshop workshop) {
        if (workshop == null) {
            return null;
        }

        return WorkshopEntity.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .address(AddressMapper.toEntity(workshop.getAddress()))
                .employees(null)
                .ownerId(workshop.getOwnerId())
                .isVisible(workshop.isVisible())
                .build();
    }
}

