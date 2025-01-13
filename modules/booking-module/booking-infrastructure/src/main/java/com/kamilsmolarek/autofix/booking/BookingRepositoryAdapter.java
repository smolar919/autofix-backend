package com.kamilsmolarek.autofix.booking;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingRepositoryAdapter implements BookingRepository{

    private final BookingRepositoryJpa bookingRepositoryJpa;

    public BookingRepositoryAdapter(BookingRepositoryJpa bookingRepositoryJpa) {
        this.bookingRepositoryJpa = bookingRepositoryJpa;
    }

    @Override
    public Booking save(Booking booking) {
        BookingEntity entity = bookingRepositoryJpa.save(BookingMapper.toEntity(booking));
        return BookingMapper.toBooking(entity);
    }

    @Override
    public Booking findById(String bookingId) {
        return bookingRepositoryJpa.findById(bookingId)
                .map(BookingMapper::toBooking).orElseThrow(() -> new ApplicationException(ErrorCode.BOOKING_NOT_FOUND));
    }

    @Override
    public List<Booking> findAllByUserId(String userId) {
        List<BookingEntity> entities = bookingRepositoryJpa.findAllByUserId(userId);
        return entities.stream()
                .map(BookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByWorkshopId(String workshopId) {
        List<BookingEntity> entities = bookingRepositoryJpa.findAllByWorkshopId(workshopId);
        return entities.stream()
                .map(BookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByEmployeeId(String employeeId) {
        List<BookingEntity> entities = bookingRepositoryJpa.findAllByEmployeeId(employeeId);
        return entities.stream()
                .map(BookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByVehicleId(String vehicleId) {
        List<BookingEntity> entities = bookingRepositoryJpa.findAllByVehicleId(vehicleId);
        return entities.stream()
                .map(BookingMapper::toBooking)
                .collect(Collectors.toList());
    }
}
