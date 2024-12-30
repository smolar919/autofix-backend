package com.kamilsmolarek.autofix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceServiceTest {
    private ServiceRepositoryMock serviceRepositoryMock;
    private ServiceService serviceService;

    @BeforeEach
    void setUp() {
        serviceRepositoryMock = new ServiceRepositoryMock();
        serviceService = new ServiceServiceImpl(serviceRepositoryMock);
    }

    @Test
    void createServiceTest() {
        CreateServiceForm form = new CreateServiceForm("Oil change", "Change oil in the engine", 100.0, "1");
        ServiceDTO service = serviceService.create(form);

        assertNotNull(service.getId());
        assertEquals("Oil change", service.getName());
        assertEquals("Change oil in the engine", service.getDescription());
        assertEquals(100.0, service.getPrice());
        assertEquals("1", service.getWorkshopId());

    }

    @Test
    void editServiceTest() {
        ServiceDTO serviceDTO = new ServiceDTO("1", "Oil change", "Change oil in the engine", 100.0, "1");
        serviceRepositoryMock.save(serviceDTO);

        EditServiceForm form = new EditServiceForm( "Oil change", "Change oil in the transmission", 200.0);
        ServiceDTO serviceDTO1 = serviceService.edit("1", form);

        assertEquals("Change oil in the transmission", serviceDTO1.getDescription());
        assertEquals(200.0, serviceDTO1.getPrice());
    }

    @Test
    void deleteVehicleTest() {
        ServiceDTO serviceDTO = new ServiceDTO("1", "Oil change", "Change oil in the engine", 100.0, "1");
        serviceRepositoryMock.save(serviceDTO);

        serviceService.delete("1");
        assertFalse(serviceRepositoryMock.findById("1").isPresent());
    }

    @Test
    void listByWorkshopIdTest() {
        ServiceDTO serviceDTO1 = new ServiceDTO("1", "Oil change", "Change oil in the engine", 100.0, "1");
        ServiceDTO serviceDTO2 = new ServiceDTO("2", "Cooling fluid change", "Change cooling fluid in the engine", 100.0, "1");
        serviceRepositoryMock.save(serviceDTO1);
        serviceRepositoryMock.save(serviceDTO2);

        List<ServiceDTO> vehicles = serviceService.listByWorkshopId("1");
        assertEquals(2, vehicles.size());
    }
}
