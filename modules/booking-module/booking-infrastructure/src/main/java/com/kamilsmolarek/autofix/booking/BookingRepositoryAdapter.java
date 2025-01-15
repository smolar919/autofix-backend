package com.kamilsmolarek.autofix.booking;

import com.kamilsmolarek.autofix.commons.SearchSpecification;
import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingRepositoryAdapter implements BookingRepository {

    private final BookingRepositoryJpa bookingRepositoryJpa;

    public BookingRepositoryAdapter(BookingRepositoryJpa bookingRepositoryJpa) {
        this.bookingRepositoryJpa = bookingRepositoryJpa;
    }

    @Override
    public Booking save(Booking booking) {
        BookingEntity entity = BookingMapper.toEntity(booking);
        BookingEntity savedEntity = bookingRepositoryJpa.save(entity);
        return BookingMapper.toBooking(savedEntity);
    }

    @Override
    public Booking findById(String bookingId) {
        return bookingRepositoryJpa.findById(bookingId)
                .map(BookingMapper::toBooking)
                .orElseThrow(() -> new ApplicationException(ErrorCode.BOOKING_NOT_FOUND));
    }

    @Override
    public List<Booking> findAllByUserId(String userId) {
        return bookingRepositoryJpa.findAllByUserId(userId)
                .stream()
                .map(BookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByWorkshopId(String workshopId) {
        return bookingRepositoryJpa.findAllByWorkshopId(workshopId)
                .stream()
                .map(BookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByEmployeeId(String employeeId) {
        return bookingRepositoryJpa.findAllByEmployeeId(employeeId)
                .stream()
                .map(BookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByVehicleId(String vehicleId) {
        return bookingRepositoryJpa.findAllByVehicleId(vehicleId)
                .stream()
                .map(BookingMapper::toBooking)
                .collect(Collectors.toList());
    }

    @Override
    public SearchResponse<Booking> search(SearchForm form) {
        Specification<BookingEntity> specification = SearchSpecification.buildSpecification(form.getCriteria());
        Page<BookingEntity> bookingPage = bookingRepositoryJpa.findAll(specification, SearchSpecification.getPageRequest(form));
        return SearchResponse.<Booking>builder()
                .items(bookingPage.getContent().stream()
                        .map(BookingMapper::toBooking)
                        .collect(Collectors.toList()))
                .total(bookingPage.getTotalElements())
                .build();
    }
}
