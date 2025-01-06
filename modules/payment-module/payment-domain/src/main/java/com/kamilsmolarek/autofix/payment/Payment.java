package com.kamilsmolarek.autofix.payment;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    private String id;
    private int amount;
    private String currency;
    private String clientSecret;
    private PaymentStatus status;
    private String workshopId;
    private String purpose;
    private LocalDate validUntil;
}
