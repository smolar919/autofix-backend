package com.kamilsmolarek.autofix.vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(String id);
    void deleteById(String id);
    List<Vehicle> findByOwnerId(String ownerId);
}
