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
                .bookingDate(booking.getBookingDate())
                .status(booking.getStatus())
                .build();
    }

    public static Booking toBooking(BookingEntity bookingEntity) {
        if (bookingEntity == null) {
            return null;
        }
        return Booking.builder()
                .id(bookingEntity.getId())
                .workshopId(bookingEntity.getWorkshopId())
                .userId(bookingEntity.getUserId())
                .vehicleId(bookingEntity.getVehicleId())
                .serviceIds(new ArrayList<>(bookingEntity.getServiceIds()))
                .employeeId(bookingEntity.getEmployeeId())
                .bookingDate(bookingEntity.getBookingDate())
                .status(bookingEntity.getStatus())
                .build();
    }
}

