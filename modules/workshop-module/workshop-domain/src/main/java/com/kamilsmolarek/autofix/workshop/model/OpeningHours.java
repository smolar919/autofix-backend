package com.kamilsmolarek.autofix.workshop.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpeningHours {
    private String id;
    private DayOfWeek dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Workshop workshop;
}
