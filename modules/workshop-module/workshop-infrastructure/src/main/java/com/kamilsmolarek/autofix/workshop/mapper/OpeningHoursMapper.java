package com.kamilsmolarek.autofix.workshop.mapper;

import com.kamilsmolarek.autofix.workshop.entity.OpeningHoursEntity;
import com.kamilsmolarek.autofix.workshop.model.OpeningHours;

public class OpeningHoursMapper {
    public static OpeningHoursEntity toEntity(OpeningHours domain) {
        OpeningHoursEntity entity = new OpeningHoursEntity();
        entity.setId(domain.getId());
        entity.setDayOfWeek(domain.getDayOfWeek());
        entity.setOpeningTime(domain.getOpeningTime());
        entity.setClosingTime(domain.getClosingTime());
        return entity;
    }

    public static OpeningHours toDomain(OpeningHoursEntity entity) {
        OpeningHours domain = new OpeningHours();
        domain.setId(entity.getId());
        domain.setDayOfWeek(entity.getDayOfWeek());
        domain.setOpeningTime(entity.getOpeningTime());
        domain.setClosingTime(entity.getClosingTime());
        return domain;
    }
}
