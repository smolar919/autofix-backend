package com.kamilsmolarek.autofix.workshop.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Workshop {
    private String id;
    private String ownerId;
    private String name;
    private Address address;
    private List<Employee> employees;
    private boolean isVisible;
    private String description;
    private List<String> serviceIds;
    private String openingHours;
}
