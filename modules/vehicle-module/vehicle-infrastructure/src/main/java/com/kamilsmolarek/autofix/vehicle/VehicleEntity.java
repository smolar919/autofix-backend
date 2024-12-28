package com.kamilsmolarek.autofix.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "vehicle", schema = "public")
public class VehicleEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "vin")
    private String vin;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "owner_id")
    private String ownerId;
}
