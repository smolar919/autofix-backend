package com.kamilsmolarek.autofix.workshop.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.DayOfWeek;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "opening_hours")
public class OpeningHoursEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "opening_time", nullable = false)
    private LocalTime openingTime;

    @Column(name = "closing_time", nullable = false)
    private LocalTime closingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop_id", nullable = false)
    private WorkshopEntity workshop;
}
