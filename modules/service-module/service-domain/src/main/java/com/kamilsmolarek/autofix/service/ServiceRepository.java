package com.kamilsmolarek.autofix.service;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    ServiceDTO save(ServiceDTO service);

    Optional<ServiceDTO> findById(String id);

    void deleteById(String id);

    List<ServiceDTO> findByWorkshopId(String workshopId);
}
