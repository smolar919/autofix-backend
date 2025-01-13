package com.kamilsmolarek.autofix.workshop.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWorkshopForm {
    private String name;
    private String street;
    private String city;
    private String postalCode;
    private String voivodeship;
    private String country;
    private String ownerId;
    private String ownerPhoneNumber;
}
