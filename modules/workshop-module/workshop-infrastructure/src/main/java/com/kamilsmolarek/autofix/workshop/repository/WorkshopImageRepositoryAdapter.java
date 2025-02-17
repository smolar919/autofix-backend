package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.workshop.entity.WorkshopImageEntity;
import com.kamilsmolarek.autofix.workshop.mapper.WorkshopImageMapper;
import com.kamilsmolarek.autofix.workshop.model.WorkshopImage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class WorkshopImageRepositoryAdapter implements WorkshopImageRepository {

    private final WorkshopImageRepositoryJpa repositoryJpa;
    private final WorkshopImageMapper mapper;

    public WorkshopImageRepositoryAdapter(WorkshopImageRepositoryJpa repositoryJpa, WorkshopImageMapper mapper) {
        this.repositoryJpa = repositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public WorkshopImage save(WorkshopImage workshopImage) {
        WorkshopImageEntity entity = mapper.toEntity(workshopImage);
        WorkshopImageEntity saved = repositoryJpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<WorkshopImage> findById(String id) {
        return repositoryJpa.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<WorkshopImage> findByWorkshopId(String workshopId) {
        return repositoryJpa.findByWorkshopId(workshopId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        repositoryJpa.deleteById(id);
    }
}
