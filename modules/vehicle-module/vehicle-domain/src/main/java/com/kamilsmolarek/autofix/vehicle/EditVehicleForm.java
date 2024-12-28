package com.kamilsmolarek.autofix.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditVehicleForm {
    private String make;
    private String model;
    private int year;
    private String vin;
    private String registrationNumber;
}
