package com.kamilsmolarek.autofix.workshop.entity;

import com.kamilsmolarek.autofix.workshop.model.TimeSlotStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "time_slot")
public class TimeSlotEntity {
    @Id
    private String id;

    @Column(name = "workshop_id", nullable = false)
    private String workshopId;

    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time", nullable = false)
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TimeSlotStatus status;
}