package com.kamilsmolarek.autofix.workshop.mapper;

import com.kamilsmolarek.autofix.workshop.entity.OpeningHoursEntity;
import com.kamilsmolarek.autofix.workshop.entity.WorkshopEntity;
import com.kamilsmolarek.autofix.workshop.model.OpeningHours;
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

        List<OpeningHours> openingHours = entity.getOpeningHours() != null && !entity.getOpeningHours().isEmpty()
                ? entity.getOpeningHours().stream()
                .map(OpeningHoursMapper::toDomain)
                .collect(Collectors.toList())
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
                .openingHours(openingHours)
                .build();
    }

    public static WorkshopEntity toEntity(Workshop workshop) {
        String serviceIds = (workshop.getServiceIds() != null && !workshop.getServiceIds().isEmpty())
                ? workshop.getServiceIds().stream().collect(Collectors.joining(","))
                : "";

        List<OpeningHoursEntity> openingHoursEntities = (workshop.getOpeningHours() != null && !workshop.getOpeningHours().isEmpty())
                ? workshop.getOpeningHours().stream()
                .map(OpeningHoursMapper::toEntity)
                .collect(Collectors.toList())
                : Collections.emptyList();

        WorkshopEntity workshopEntity = WorkshopEntity.builder()
                .id(workshop.getId())
                .name(workshop.getName())
                .address(AddressMapper.toEntity(workshop.getAddress()))
                .employees(null)
                .ownerId(workshop.getOwnerId())
                .isVisible(workshop.isVisible())
                .description(workshop.getDescription())
                .serviceIds(serviceIds)
                .openingHours(openingHoursEntities)
                .build();

        if (openingHoursEntities != null) {
            openingHoursEntities.forEach(oh -> oh.setWorkshop(workshopEntity));
        }

        return workshopEntity;
    }

}

