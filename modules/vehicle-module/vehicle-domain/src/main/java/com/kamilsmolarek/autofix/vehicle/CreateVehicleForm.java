package com.kamilsmolarek.autofix.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateVehicleForm {
    private String make;
    private String model;
    private int year;
    private String vin;
    private String registrationNumber;
    private String ownerId;
}
