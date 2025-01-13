package com.kamilsmolarek.autofix.workshop.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TimeSlot {
    private String id;
    private String workshopId;
    private String employeeId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private TimeSlotStatus status;
}
