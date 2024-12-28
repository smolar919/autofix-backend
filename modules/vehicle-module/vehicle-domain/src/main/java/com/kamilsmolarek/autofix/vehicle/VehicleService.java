package com.kamilsmolarek.autofix.vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle create(CreateVehicleForm form);
    Vehicle edit(String id, EditVehicleForm form);
    void delete(String id);
    List<Vehicle> listByOwnerId(String ownerId);
}
