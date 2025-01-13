package com.kamilsmolarek.autofix.booking;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateBookingForm {
    private String workshopId;
    private String userId;
    private String vehicleId;
    private List<String> serviceIds;
    private String employeeId;
    private LocalDateTime bookingDate;
}
