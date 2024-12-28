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
                .employees(workshopEntity.getEmployees() != null
                        ? workshopEntity.getEmployees().stream()
                        .map(EmployeeMapper::toEmployee)
                        .collect(Collectors.toList())
                        : null)
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
                .employees(workshop.getEmployees() != null
                        ? workshop.getEmployees().stream()
                        .map(EmployeeMapper::toEntity)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }
}

