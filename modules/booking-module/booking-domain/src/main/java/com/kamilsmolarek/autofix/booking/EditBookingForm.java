package com.kamilsmolarek.autofix.booking;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EditBookingForm {
    private String bookingId;
    private LocalDateTime newCompletionDate;  // Data zakończenia rezerwacji
    private String workDescription;           // Opis wykonanych prac
}
