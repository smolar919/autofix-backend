package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.workshop.mapper.TimeSlotMapper;
import com.kamilsmolarek.autofix.workshop.model.TimeSlot;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TimeSlotRepositoryAdapter implements TimeSlotRepository {

    private final TimeSlotRepositoryJpa repositoryJpa;

    public TimeSlotRepositoryAdapter(TimeSlotRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public TimeSlot save(TimeSlot timeSlot) {
        return TimeSlotMapper.toDomain(repositoryJpa.save(TimeSlotMapper.toEntity(timeSlot)));
    }

    @Override
    public Optional<TimeSlot> findById(String id) {
        return repositoryJpa.findById(id).map(TimeSlotMapper::toDomain);
    }

    @Override
    public List<TimeSlot> findByWorkshopId(String workshopId) {
        return repositoryJpa.findByWorkshopId(workshopId)
                .stream()
                .map(TimeSlotMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repositoryJpa.deleteById(id);
    }
}