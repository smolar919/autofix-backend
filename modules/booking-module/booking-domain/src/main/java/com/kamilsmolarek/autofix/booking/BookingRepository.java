package com.kamilsmolarek.autofix.booking;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;

import java.util.List;

public interface BookingRepository {
    Booking save(Booking booking);
    Booking findById(String bookingId);
    List<Booking> findAllByUserId(String userId);
    List<Booking> findAllByWorkshopId(String workshopId);
    List<Booking> findAllByEmployeeId(String employeeId);
    List<Booking> findAllByVehicleId(String vehicleId);

    SearchResponse<Booking> search(SearchForm form);
}
