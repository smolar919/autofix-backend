package com.kamilsmolarek.autofix.vehicle;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Vehicle {
    private String id;
    private String make;
    private String model;
    private int year;
    private String vin;
    private String registrationNumber;
    private String ownerId;
}
