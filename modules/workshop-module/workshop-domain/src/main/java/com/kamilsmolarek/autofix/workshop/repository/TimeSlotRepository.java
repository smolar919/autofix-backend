package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.workshop.model.TimeSlot;

import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository {
    TimeSlot save(TimeSlot timeSlot);
    Optional<TimeSlot> findById(String id);
    List<TimeSlot> findByWorkshopId(String workshopId);
    void deleteById(String id);
}
