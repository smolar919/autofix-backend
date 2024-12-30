package com.kamilsmolarek.autofix.service;

public class ServiceMapper {
    public static ServiceDTO toServiceDTO(ServiceEntity serviceEntity) {
        return ServiceDTO.builder()
                .id(serviceEntity.getId())
                .name(serviceEntity.getName())
                .description(serviceEntity.getDescription())
                .price(serviceEntity.getPrice())
                .workshopId(serviceEntity.getWorkshopId())
                .build();
    }

    public static ServiceEntity toEntity(ServiceDTO serviceDTO) {
        return ServiceEntity.builder()
                .id(serviceDTO.getId())
                .name(serviceDTO.getName())
                .description(serviceDTO.getDescription())
                .price(serviceDTO.getPrice())
                .workshopId(serviceDTO.getWorkshopId())
                .build();
    }
}
