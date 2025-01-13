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

    @Column(name = "workshop_id")
    private String workshopId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @ElementCollection
    @CollectionTable(name = "booking_service_ids", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "service_id")
    private List<String> serviceIds;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;
}

