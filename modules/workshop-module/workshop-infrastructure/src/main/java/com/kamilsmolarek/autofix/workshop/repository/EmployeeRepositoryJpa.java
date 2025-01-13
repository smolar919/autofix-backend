package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.workshop.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmployeeRepositoryJpa extends JpaRepository<EmployeeEntity, String>, JpaSpecificationExecutor<EmployeeEntity> {
    List<EmployeeEntity> findByWorkshopId(String workshopId);
    List<EmployeeEntity> findByUserId(String userId);
}
