package com.kamilsmolarek.autofix.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingRepositoryMock implements BookingRepository {

    private final Map<String, Booking> bookingMap = new HashMap<>();

    @Override
    public Booking save(Booking booking) {
        bookingMap.put(booking.getId(), booking);
        return booking;
    }

    @Override
    public Booking findById(String bookingId) {
        return bookingMap.get(bookingId);
    }

    @Override
    public List<Booking> findAllByUserId(String userId) {
        return bookingMap.values().stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByWorkshopId(String workshopId) {
        return bookingMap.values().stream()
                .filter(booking -> booking.getWorkshopId().equals(workshopId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByEmployeeId(String employeeId) {
        return bookingMap.values().stream()
                .filter(booking -> booking.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByVehicleId(String vehicleId) {
        return bookingMap.values().stream()
                .filter(booking -> booking.getVehicleId().equals(vehicleId))
                .collect(Collectors.toList());
    }
}
