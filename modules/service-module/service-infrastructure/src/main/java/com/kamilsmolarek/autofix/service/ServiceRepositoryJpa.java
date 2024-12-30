package com.kamilsmolarek.autofix.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepositoryJpa extends JpaRepository<ServiceEntity, String> {
    List<ServiceEntity> findByWorkshopId(String workshopId);
}
