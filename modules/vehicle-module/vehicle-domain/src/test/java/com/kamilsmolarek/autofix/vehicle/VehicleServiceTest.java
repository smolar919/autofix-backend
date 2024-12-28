package com.kamilsmolarek.autofix.vehicle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleServiceTest {

    private VehicleRepositoryMock vehicleRepositoryMock;
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleRepositoryMock = new VehicleRepositoryMock();
        vehicleService = new VehicleServiceImpl(vehicleRepositoryMock);
    }

    @Test
    void createVehicleTest() {
        CreateVehicleForm form = new CreateVehicleForm("Toyota", "Corolla", 2021, "vin","user123", "1");
        Vehicle vehicle = vehicleService.create(form);

        assertNotNull(vehicle.getId());
        assertEquals("Toyota", vehicle.getMake());
        assertEquals("Corolla", vehicle.getModel());
        assertEquals(2021, vehicle.getYear());
        assertEquals("1", vehicle.getOwnerId());
        assertEquals("vin", vehicle.getVin());
        assertEquals("user123", vehicle.getRegistrationNumber());
    }

    @Test
    void editVehicleTest() {
        Vehicle vehicle = new Vehicle("1","Toyota", "Corolla", 2021, "vin","user123", "1");
        vehicleRepositoryMock.save(vehicle);

        EditVehicleForm form = new EditVehicleForm( "Toyota", "Corolla", 2022, "vin2","user123");
        Vehicle updatedVehicle = vehicleService.edit("1", form);

        assertEquals(2022, updatedVehicle.getYear());
        assertEquals("vin2", updatedVehicle.getVin());
    }

    @Test
    void deleteVehicleTest() {
        Vehicle vehicle = new Vehicle("1","Toyota", "Corolla", 2021, "vin","user123", "1");
        vehicleRepositoryMock.save(vehicle);

        vehicleService.delete("1");
        assertFalse(vehicleRepositoryMock.findById("1").isPresent());
    }

    @Test
    void listByOwnerIdTest() {
        Vehicle vehicle1 = new Vehicle("1","Toyota", "Corolla", 2021, "vin","user123", "1");
        Vehicle vehicle2 = new Vehicle("2","Honda", "Civic", 2024, "vin2","civic123", "1");
        vehicleRepositoryMock.save(vehicle1);
        vehicleRepositoryMock.save(vehicle2);

        List<Vehicle> vehicles = vehicleService.listByOwnerId("1");
        assertEquals(2, vehicles.size());
    }
}
