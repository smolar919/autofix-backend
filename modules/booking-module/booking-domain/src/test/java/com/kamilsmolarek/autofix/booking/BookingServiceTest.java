package com.kamilsmolarek.autofix.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        BookingRepository bookingRepository = new BookingRepositoryMock();
        bookingService = new BookingServiceImpl(bookingRepository);
    }

    @Test
    public void testCreateBooking() {
        CreateBookingForm form = CreateBookingForm.builder()
                .userId("user1")
                .workshopId("workshop1")
                .vehicleId("vehicle1")
                .serviceIds(List.of("service1"))
                .employeeId("employee1")
                .bookingDate(LocalDateTime.now())
                .build();

        Booking booking = bookingService.createBooking(form);

        assertNotNull(booking.getId());
        assertEquals("user1", booking.getUserId());
        assertEquals("workshop1", booking.getWorkshopId());
        assertEquals("vehicle1", booking.getVehicleId());
        assertEquals(BookingStatus.PENDING, booking.getStatus());
    }

    @Test
    public void testUpdateBooking() {
        CreateBookingForm form = CreateBookingForm.builder()
                .userId("user1")
                .workshopId("workshop1")
                .vehicleId("vehicle1")
                .serviceIds(List.of("service1"))
                .employeeId("employee1")
                .bookingDate(LocalDateTime.now())
                .build();

        Booking booking = bookingService.createBooking(form);
        String bookingId = booking.getId();

        EditBookingForm editForm = new EditBookingForm(bookingId, LocalDateTime.now().plusDays(1));

        Booking updatedBooking = bookingService.updateBooking(editForm);

        assertEquals(editForm.getNewDate(), updatedBooking.getBookingDate());
    }

    @Test
    public void testCancelBooking() {
        CreateBookingForm form = CreateBookingForm.builder()
                .userId("user1")
                .workshopId("workshop1")
                .vehicleId("vehicle1")
                .serviceIds(List.of("service1"))
                .employeeId("employee1")
                .bookingDate(LocalDateTime.now())
                .build();

        Booking booking = bookingService.createBooking(form);
        String bookingId = booking.getId();

        bookingService.cancelBooking(bookingId);

        Booking cancelledBooking = bookingService.findBookingById(bookingId);

        assertEquals(BookingStatus.CANCELLED, cancelledBooking.getStatus());
    }

    @Test
    public void testFindBookingById() {
        CreateBookingForm form = CreateBookingForm.builder()
                .userId("user1")
                .workshopId("workshop1")
                .vehicleId("vehicle1")
                .serviceIds(List.of("service1"))
                .employeeId("employee1")
                .bookingDate(LocalDateTime.now())
                .build();

        Booking booking = bookingService.createBooking(form);
        String bookingId = booking.getId();

        Booking foundBooking = bookingService.findBookingById(bookingId);

        assertNotNull(foundBooking);
        assertEquals(bookingId, foundBooking.getId());
    }

    @Test
    public void testFindBookingByUserId() {
        CreateBookingForm form = CreateBookingForm.builder()
                .userId("user1")
                .workshopId("workshop1")
                .vehicleId("vehicle1")
                .serviceIds(List.of("service1"))
                .employeeId("employee1")
                .bookingDate(LocalDateTime.now())
                .build();

        bookingService.createBooking(form);

        List<Booking> bookings = bookingService.findBookingByUserId("user1");

        assertEquals(1, bookings.size());
        assertEquals("user1", bookings.get(0).getUserId());
    }

    @Test
    public void testFindBookingByVehicleId() {
        CreateBookingForm form = CreateBookingForm.builder()
                .userId("user1")
                .workshopId("workshop1")
                .vehicleId("vehicle1")
                .serviceIds(List.of("service1"))
                .employeeId("employee1")
                .bookingDate(LocalDateTime.now())
                .build();

        bookingService.createBooking(form);

        List<Booking> bookings = bookingService.findBookingByVehicleId("vehicle1");

        assertEquals(1, bookings.size());
        assertEquals("vehicle1", bookings.get(0).getVehicleId());
    }

    @Test
    public void testFindBookingByWorkshopId() {
        CreateBookingForm form = CreateBookingForm.builder()
                .userId("user1")
                .workshopId("workshop1")
                .vehicleId("vehicle1")
                .serviceIds(List.of("service1"))
                .employeeId("employee1")
                .bookingDate(LocalDateTime.now())
                .build();

        bookingService.createBooking(form);

        List<Booking> bookings = bookingService.findBookingByWorkshopId("workshop1");

        assertEquals(1, bookings.size());
        assertEquals("workshop1", bookings.get(0).getWorkshopId());
    }

    @Test
    public void testFindBookingByEmployeeId() {
        CreateBookingForm form = CreateBookingForm.builder()
                .userId("user1")
                .workshopId("workshop1")
                .vehicleId("vehicle1")
                .serviceIds(List.of("service1"))
                .employeeId("employee1")
                .bookingDate(LocalDateTime.now())
                .build();

        bookingService.createBooking(form);

        List<Booking> bookings = bookingService.findBookingByEmployeeId("employee1");

        assertEquals(1, bookings.size());
        assertEquals("employee1", bookings.get(0).getEmployeeId());
    }
}

