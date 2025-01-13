package com.kamilsmolarek.autofix.workshop.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateNewEmployeeForm {
    private String firstName;
    private String lastName;
    private String position;
    private String phoneNumber;
    private String email;
    private String workshopId;
    private String password;
}
