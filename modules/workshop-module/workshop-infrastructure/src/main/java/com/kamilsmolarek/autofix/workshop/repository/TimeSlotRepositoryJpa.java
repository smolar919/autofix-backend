package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.workshop.entity.TimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeSlotRepositoryJpa extends JpaRepository<TimeSlotEntity, String> {
    List<TimeSlotEntity> findByWorkshopId(String workshopId);
}
