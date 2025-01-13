package com.kamilsmolarek.autofix.workshop.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddExistingEmployeeForm {
    private String email;
    private String position;
    private String phoneNumber;
    private String workshopId;
}
