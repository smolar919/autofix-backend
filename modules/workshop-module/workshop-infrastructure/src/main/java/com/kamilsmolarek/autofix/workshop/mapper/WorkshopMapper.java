package com.kamilsmolarek.autofix.workshop.mapper;

import com.kamilsmolarek.autofix.workshop.entity.WorkshopEntity;
import com.kamilsmolarek.autofix.workshop.model.Workshop;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WorkshopMapper {
    public static Workshop toWorkshop(WorkshopEntity entity) {
        if (entity == null) {
            return null;
        }
        List<String> serviceIds = entity.getServiceIds() != null && !entity.getServiceIds().isEmpty()
                ? Arrays.asList(entity.getServiceIds().split(","))
                : Collections.emptyList();
        return Workshop.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(AddressMapper.toAddress(entity.getAddress()))
                .employees(null)
                .ownerId(entity.getOwnerId())
                .isVisible(entity.isVisible())
                .description(entity.getDescription())
                .serviceIds(serviceIds)
                .openingHours(entity.getOpeningHours())
                .build();
    }

    public static WorkshopEntity toEntity(Workshop workshop) {
        String serviceIds = (workshop.getServiceIds() != null && !workshop.getServiceIds().isEmpty())
                ? workshop.getServiceIds().stream().collect(Collectors.joining(","))
                : "";
        return WorkshopEntity.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .address(AddressMapper.toEntity(workshop.getAddress()))
                .employees(null)
                .ownerId(workshop.getOwnerId())
                .isVisible(workshop.isVisible())
                .description(workshop.getDescription())
                .serviceIds(serviceIds)
                .openingHours(workshop.getOpeningHours())
                .build();
    }
}

