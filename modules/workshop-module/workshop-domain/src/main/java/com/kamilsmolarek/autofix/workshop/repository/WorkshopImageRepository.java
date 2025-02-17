package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.workshop.model.WorkshopImage;

import java.util.List;
import java.util.Optional;

public interface WorkshopImageRepository {
    WorkshopImage save(WorkshopImage workshopImage);
    Optional<WorkshopImage> findById(String id);
    List<WorkshopImage> findByWorkshopId(String workshopId);
    void deleteById(String id);
}
