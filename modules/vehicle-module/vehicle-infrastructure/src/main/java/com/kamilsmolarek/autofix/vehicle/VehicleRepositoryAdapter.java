package com.kamilsmolarek.autofix.vehicle;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VehicleRepositoryAdapter implements VehicleRepository {

    private final VehicleRepositoryJpa repositoryJpa;

    public VehicleRepositoryAdapter(VehicleRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleEntity entity = repositoryJpa.save(VehicleMapper.toEntity(vehicle));
        return VehicleMapper.toModel(entity);
    }

    @Override
    public Optional<Vehicle> findById(String id) {
        return repositoryJpa.findById(id).map(VehicleMapper::toModel);
    }

    @Override
    public void deleteById(String id) {
        repositoryJpa.deleteById(id);
    }

    @Override
    public List<Vehicle> findByOwnerId(String ownerId) {
        return repositoryJpa.findByOwnerId(ownerId).stream()
                .map(VehicleMapper::toModel)
                .collect(Collectors.toList());
    }
}
