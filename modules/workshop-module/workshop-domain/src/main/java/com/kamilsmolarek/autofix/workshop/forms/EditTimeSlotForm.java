package com.kamilsmolarek.autofix.workshop.forms;

import com.kamilsmolarek.autofix.workshop.model.TimeSlotStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EditTimeSlotForm {
    private String id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private TimeSlotStatus status;
    private String employeeId;
}
