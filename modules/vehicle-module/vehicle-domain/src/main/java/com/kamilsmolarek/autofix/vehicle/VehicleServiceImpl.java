package com.kamilsmolarek.autofix.vehicle;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle create(CreateVehicleForm form) {
        Vehicle vehicle = Vehicle.builder()
                .id(UUID.randomUUID().toString())
                .make(form.getMake())
                .model(form.getModel())
                .year(form.getYear())
                .vin(form.getVin())
                .registrationNumber(form.getRegistrationNumber())
                .ownerId(form.getOwnerId())
                .build();
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle edit(String id, EditVehicleForm form) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.VEHICLE_NOT_FOUND));

        existingVehicle.setMake(form.getMake());
        existingVehicle.setModel(form.getModel());
        existingVehicle.setYear(form.getYear());
        existingVehicle.setVin(form.getVin());
        existingVehicle.setRegistrationNumber(form.getRegistrationNumber());

        return vehicleRepository.save(existingVehicle);
    }

    @Override
    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicle> listByOwnerId(String ownerId) {
        return vehicleRepository.findByOwnerId(ownerId);
    }
}
