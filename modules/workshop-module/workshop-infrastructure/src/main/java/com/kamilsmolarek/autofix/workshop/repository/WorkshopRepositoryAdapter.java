package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.commons.SearchSpecification;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.entity.WorkshopEntity;
import com.kamilsmolarek.autofix.workshop.mapper.WorkshopMapper;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class WorkshopRepositoryAdapter implements WorkshopRepository {
    private final WorkshopRepositoryJpa repositoryJpa;

    public WorkshopRepositoryAdapter(WorkshopRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Workshop save(Workshop workshop) {
        WorkshopEntity entity = WorkshopMapper.toEntity(workshop);
        WorkshopEntity savedEntity = repositoryJpa.save(entity);
        return WorkshopMapper.toWorkshop(savedEntity);
    }

    @Override
    public Optional<Workshop> findById(String id) {
        Optional<WorkshopEntity> entity = repositoryJpa.findById(id);
        return entity.map(WorkshopMapper::toWorkshop);
    }

    @Override
    public void deleteById(String id) {
        repositoryJpa.deleteById(id);
    }

    @Override
    public SearchResponse<Workshop> search(SearchForm form) {
        Specification<WorkshopEntity> specification = SearchSpecification.buildSpecification(form.getCriteria());
        Page<WorkshopEntity> workshopPage = repositoryJpa.findAll(specification, SearchSpecification.getPageRequest(form));
        return SearchResponse.<Workshop>builder()
                .items(workshopPage.getContent().stream()
                        .map(WorkshopMapper::toWorkshop)
                        .collect(Collectors.toList()))
                .total(workshopPage.getTotalElements())
                .build();
    }
}
