package com.kamilsmolarek.autofix.workshop.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    private String id;
    private String street;
    private String city;
    private String postalCode;
    private String voivodeship;
    private String country;
}
