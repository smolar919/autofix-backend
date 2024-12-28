package com.kamilsmolarek.autofix.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepositoryJpa extends JpaRepository<VehicleEntity, String> {
    List<VehicleEntity> findByOwnerId(String ownerId);
}
