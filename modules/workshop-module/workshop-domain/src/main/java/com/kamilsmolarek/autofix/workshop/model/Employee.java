package com.kamilsmolarek.autofix.workshop.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private String phoneNumber;
    private String email;
    private String workshopId;
}
