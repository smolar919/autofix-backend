package com.kamilsmolarek.autofix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceRepositoryMock implements ServiceRepository{

    private final List<ServiceDTO> serviceDTOS = new ArrayList<>();

    @Override
    public ServiceDTO save(ServiceDTO service) {
        serviceDTOS.removeIf(v -> v.getId().equals(service.getId()));
        serviceDTOS.add(service);
        return service;
    }

    @Override
    public Optional<ServiceDTO> findById(String id) {
        return serviceDTOS.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteById(String id) {
        serviceDTOS.removeIf(v -> v.getId().equals(id));
    }

    @Override
    public List<ServiceDTO> findByWorkshopId(String workshopId) {
        return serviceDTOS.stream().filter(v -> v.getWorkshopId().equals(workshopId)).toList();
    }
}
