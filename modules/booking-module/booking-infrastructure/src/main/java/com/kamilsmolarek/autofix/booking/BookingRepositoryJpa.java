package com.kamilsmolarek.autofix.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepositoryJpa extends JpaRepository<BookingEntity, String> {
    List<BookingEntity> findAllByUserId(String userId);
    List<BookingEntity> findAllByWorkshopId(String workshopId);
    List<BookingEntity> findAllByEmployeeId(String employeeId);
    List<BookingEntity> findAllByVehicleId(String vehicleId);
}
