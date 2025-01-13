package com.kamilsmolarek.autofix.workshop.mapper;

import com.kamilsmolarek.autofix.workshop.entity.TimeSlotEntity;
import com.kamilsmolarek.autofix.workshop.model.TimeSlot;

public class TimeSlotMapper {
    public static TimeSlotEntity toEntity(TimeSlot domain) {
        TimeSlotEntity entity = new TimeSlotEntity();
        entity.setId(domain.getId());
        entity.setWorkshopId(domain.getWorkshopId());
        entity.setStartDateTime(domain.getStartDateTime());
        entity.setEndDateTime(domain.getEndDateTime());
        entity.setStatus(domain.getStatus());
        entity.setEmployeeId(domain.getEmployeeId());
        return entity;
    }

    public static TimeSlot toDomain(TimeSlotEntity entity) {
        TimeSlot domain = new TimeSlot();
        domain.setId(entity.getId());
        domain.setWorkshopId(entity.getWorkshopId());
        domain.setStartDateTime(entity.getStartDateTime());
        domain.setEndDateTime(entity.getEndDateTime());
        domain.setStatus(entity.getStatus());
        domain.setEmployeeId(entity.getEmployeeId());
        return domain;
    }
}