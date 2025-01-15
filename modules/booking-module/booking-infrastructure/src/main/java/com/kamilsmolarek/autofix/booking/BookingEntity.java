package com.kamilsmolarek.autofix.booking;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "booking")
@Entity
public class BookingEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "workshop_id", nullable = false)
    private String workshopId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "vehicle_id", nullable = false)
    private String vehicleId;

    @ElementCollection
    @CollectionTable(name = "booking_service_ids", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "service_id")
    private List<String> serviceIds;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "time_slot_id", nullable = false)
    private String timeSlotId;

    @Column(name = "submission_date")
    private LocalDateTime submissionDate;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @Column(name = "fault_description", length = 1024)
    private String faultDescription;

    @Column(name = "work_description", length = 1024)
    private String workDescription;
}

