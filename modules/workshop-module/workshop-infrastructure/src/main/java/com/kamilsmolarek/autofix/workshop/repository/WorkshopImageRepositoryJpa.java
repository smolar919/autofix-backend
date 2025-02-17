package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.workshop.entity.WorkshopImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkshopImageRepositoryJpa extends JpaRepository<WorkshopImageEntity, String> {
    List<WorkshopImageEntity> findByWorkshopId(String workshopId);
}
