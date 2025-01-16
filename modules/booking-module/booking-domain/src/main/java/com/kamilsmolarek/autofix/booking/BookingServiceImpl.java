package com.kamilsmolarek.autofix.booking;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                .id(UUID.randomUUID().toString())
                .workshopId(form.getWorkshopId())
                .userId(form.getUserId())
                .vehicleId(form.getVehicleId())
                .serviceIds(form.getServiceIds())
                .employeeId(form.getEmployeeId())
                .timeSlotId(form.getTimeSlotId())
                .submissionDate(LocalDateTime.now()) // zakładamy, że submissionDate jest równa bookingDate przy tworzeniu
                .status(BookingStatus.PENDING)
                .faultDescription(form.getFaultDescription())
                .build();
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(EditBookingForm form) {
        Booking booking = bookingRepository.findById(form.getBookingId());
        if (booking == null) {
            throw new ApplicationException(ErrorCode.BOOKING_NOT_FOUND);
        }
        booking.setCompletionDate(form.getNewCompletionDate());
        booking.setWorkDescription(form.getWorkDescription());
        booking.setStatus(BookingStatus.COMPLETED);
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new ApplicationException(ErrorCode.BOOKING_NOT_FOUND);
        }
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public Booking findBookingById(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
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
        if (booking == null) {
            throw new ApplicationException(ErrorCode.BOOKING_NOT_FOUND);
        }
        booking.setStatus(newStatus);
        bookingRepository.save(booking);
    }

    @Override
    public SearchResponse<Booking> search(SearchForm form) {
        return bookingRepository.search(form);
    }
}
