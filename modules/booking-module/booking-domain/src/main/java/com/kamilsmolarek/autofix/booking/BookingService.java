package com.kamilsmolarek.autofix.booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(CreateBookingForm form);
    Booking updateBooking(EditBookingForm form);
    void cancelBooking(String bookingId);
    Booking findBookingById(String bookingId);
    List<Booking> findBookingByUserId(String userId);
    List<Booking> findBookingByVehicleId(String vehicleId);
    List<Booking> findBookingByWorkshopId(String workshopId);
    List<Booking> findBookingByEmployeeId(String employeeId);
    void updateBookingStatus(String bookingId, BookingStatus newStatus);
}
