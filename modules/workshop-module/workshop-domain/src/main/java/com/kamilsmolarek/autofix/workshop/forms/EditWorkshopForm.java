package com.kamilsmolarek.autofix.workshop.forms;

import com.kamilsmolarek.autofix.workshop.model.OpeningHours;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditWorkshopForm {
    private String name;
    private String street;
    private String city;
    private String postalCode;
    private String voivodeship;
    private String country;
    private String description;
    private List<OpeningHours> openingHours;
    private List<String> serviceIds;
}
