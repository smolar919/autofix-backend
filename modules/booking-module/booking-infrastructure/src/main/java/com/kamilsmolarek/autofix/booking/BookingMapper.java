package com.kamilsmolarek.autofix.booking;

import java.util.ArrayList;

public class BookingMapper {
    public static BookingEntity toEntity(Booking booking) {
        if (booking == null) {
            return null;
        }
        return BookingEntity.builder()
                .id(booking.getId())
                .workshopId(booking.getWorkshopId())
                .userId(booking.getUserId())
                .vehicleId(booking.getVehicleId())
                .serviceIds(booking.getServiceIds())
                .employeeId(booking.getEmployeeId())
                .timeSlotId(booking.getTimeSlotId())
                .submissionDate(booking.getSubmissionDate())
                .completionDate(booking.getCompletionDate())
                .status(booking.getStatus())
                .faultDescription(booking.getFaultDescription())
                .workDescription(booking.getWorkDescription())
                .build();
    }

    public static Booking toBooking(BookingEntity entity) {
        if (entity == null) {
            return null;
        }
        return Booking.builder()
                .id(entity.getId())
                .workshopId(entity.getWorkshopId())
                .userId(entity.getUserId())
                .vehicleId(entity.getVehicleId())
                .serviceIds(new ArrayList<>(entity.getServiceIds()))
                .employeeId(entity.getEmployeeId())
                .timeSlotId(entity.getTimeSlotId())
                .submissionDate(entity.getSubmissionDate())
                .completionDate(entity.getCompletionDate())
                .status(entity.getStatus())
                .faultDescription(entity.getFaultDescription())
                .workDescription(entity.getWorkDescription())
                .build();
    }
}
