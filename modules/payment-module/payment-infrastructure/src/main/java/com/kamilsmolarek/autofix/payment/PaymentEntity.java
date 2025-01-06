package com.kamilsmolarek.autofix.payment;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "client_secret")
    private String clientSecret;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(name = "workshop_id")
    private String workshopId;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "valid_until")
    private LocalDate validUntil;
}
