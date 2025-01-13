package com.kamilsmolarek.autofix.workshop.forms;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateTimeSlotForm {
    private String workshopId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String employeeId;
}