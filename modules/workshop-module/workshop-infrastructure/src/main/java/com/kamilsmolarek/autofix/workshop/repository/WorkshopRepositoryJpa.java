package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.workshop.entity.WorkshopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkshopRepositoryJpa extends JpaRepository<WorkshopEntity, String>, JpaSpecificationExecutor<WorkshopEntity> {
}
