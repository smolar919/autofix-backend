package com.kamilsmolarek.autofix.vehicle;

public class VehicleMapper {

    public static Vehicle toModel(VehicleEntity entity) {
        return Vehicle.builder()
                .id(entity.getId())
                .make(entity.getMake())
                .model(entity.getModel())
                .year(entity.getYear())
                .vin(entity.getVin())
                .registrationNumber(entity.getRegistrationNumber())
                .ownerId(entity.getOwnerId())
                .build();
    }

    public static VehicleEntity toEntity(Vehicle vehicle) {

        return VehicleEntity.builder()
                .id(vehicle.getId())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .vin(vehicle.getVin())
                .registrationNumber(vehicle.getRegistrationNumber())
                .ownerId(vehicle.getOwnerId())
                .build();
    }
}
