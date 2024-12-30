package com.kamilsmolarek.autofix.service;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ServiceRepositoryAdapter implements ServiceRepository{
    private final ServiceRepositoryJpa repositoryJpa;

    public ServiceRepositoryAdapter(ServiceRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public ServiceDTO save(ServiceDTO service) {
        ServiceEntity entity = repositoryJpa.save(ServiceMapper.toEntity(service));
        return ServiceMapper.toServiceDTO(entity);
    }

    @Override
    public Optional<ServiceDTO> findById(String id) {
        return repositoryJpa.findById(id).map(ServiceMapper::toServiceDTO);
    }

    @Override
    public void deleteById(String id) {
        repositoryJpa.deleteById(id);
    }

    @Override
    public List<ServiceDTO> findByWorkshopId(String workshopId) {
        return repositoryJpa.findByWorkshopId(workshopId).stream()
                .map(ServiceMapper::toServiceDTO)
                .collect(Collectors.toList());    }
}
