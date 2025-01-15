package com.kamilsmolarek.autofix.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookingRepositoryJpa extends JpaRepository<BookingEntity, String>, JpaSpecificationExecutor<BookingEntity> {
    List<BookingEntity> findAllByUserId(String userId);
    List<BookingEntity> findAllByWorkshopId(String workshopId);
    List<BookingEntity> findAllByEmployeeId(String employeeId);
    List<BookingEntity> findAllByVehicleId(String vehicleId);
}
