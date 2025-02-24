package com.kamilsmolarek.autofix.workshop.forms;


import com.kamilsmolarek.autofix.workshop.model.OpeningHours;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWorkshopForm {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @NotBlank(message = "Voivodeship is required")
    private String voivodeship;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Owner ID is required")
    private String ownerId;

    @NotBlank(message = "Owner phone number is required")
    private String ownerPhoneNumber;

    @Size(max = 1024, message = "Description must be less than 1024 characters")
    private String description;

    @NotNull(message = "Opening hours are required")
    private List<OpeningHours> openingHours;

    private List<String> serviceIds;
}
