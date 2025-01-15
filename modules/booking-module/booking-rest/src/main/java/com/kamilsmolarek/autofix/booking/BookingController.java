package com.kamilsmolarek.autofix.booking;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody CreateBookingForm form) {
        return bookingService.createBooking(form);
    }

    @PutMapping("/{bookingId}")
    public Booking updateBooking(@PathVariable String bookingId, @RequestBody EditBookingForm form) {
        form.setBookingId(bookingId);
        return bookingService.updateBooking(form);
    }

    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable String bookingId) {
        return bookingService.findBookingById(bookingId);
    }

    @GetMapping("/user/{userId}/bookings")
    public List<Booking> getBookingsByUserId(@PathVariable String userId) {
        return bookingService.findBookingByUserId(userId);
    }

    @GetMapping("/vehicles/{vehicleId}/bookings")
    public List<Booking> getBookingsByVehicleId(@PathVariable String vehicleId) {
        return bookingService.findBookingByVehicleId(vehicleId);
    }

    @GetMapping("/workshop/{workshopId}/bookings")
    public List<Booking> getBookingsByWorkshopId(@PathVariable String workshopId) {
        return bookingService.findBookingByWorkshopId(workshopId);
    }

    @GetMapping("/employee/{employeeId}/bookings")
    public List<Booking> getBookingsByEmployeeId(@PathVariable String employeeId) {
        return bookingService.findBookingByEmployeeId(employeeId);
    }

    @PutMapping("/{bookingId}/status")
    public Booking updateBookingStatus(
            @PathVariable String bookingId,
            @RequestParam BookingStatus newStatus) {
        bookingService.updateBookingStatus(bookingId, newStatus);
        return bookingService.findBookingById(bookingId);
    }

    @PostMapping("/search")
    public SearchResponse<Booking> search(@RequestBody SearchForm form) {
        return bookingService.search(form);
    }
}
