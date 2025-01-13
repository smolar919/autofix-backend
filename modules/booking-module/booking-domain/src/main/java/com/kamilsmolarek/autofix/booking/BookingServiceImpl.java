package com.kamilsmolarek.autofix.booking;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(CreateBookingForm form) {
        Booking booking = Booking.builder()
                .bookingDate(form.getBookingDate())
                .id(UUID.randomUUID().toString())
                .employeeId(form.getEmployeeId())
                .serviceIds(form.getServiceIds())
                .status(BookingStatus.PENDING)
                .userId(form.getUserId())
                .vehicleId(form.getVehicleId())
                .workshopId(form.getWorkshopId())
                .build();
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(EditBookingForm form) {
        Booking booking = bookingRepository.findById(form.getBookingId());
        if(booking == null) {
            throw new ApplicationException(ErrorCode.BOOKING_NOT_FOUND);
        }
        booking.setBookingDate(form.getNewDate());
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if(booking == null) {
            throw new ApplicationException(ErrorCode.BOOKING_NOT_FOUND);
        }
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public Booking findBookingById(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if(booking == null) {
            throw new ApplicationException(ErrorCode.BOOKING_NOT_FOUND);
        }
        return booking;
    }

    @Override
    public List<Booking> findBookingByUserId(String userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Booking> findBookingByVehicleId(String vehicleId) {
        return bookingRepository.findAllByVehicleId(vehicleId);
    }

    @Override
    public List<Booking> findBookingByWorkshopId(String workshopId) {
        return bookingRepository.findAllByWorkshopId(workshopId);
    }

    @Override
    public List<Booking> findBookingByEmployeeId(String employeeId) {
        return bookingRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    public void updateBookingStatus(String bookingId, BookingStatus newStatus) {
        Booking booking = bookingRepository.findById(bookingId);
//        if(booking == null) {
//            throw new ApplicationException(ErrorCode.BOOKING_NOT_FOUND);
//        }
        booking.setStatus(newStatus);
        bookingRepository.save(booking);
    }
}
