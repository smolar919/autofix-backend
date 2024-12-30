package com.kamilsmolarek.autofix.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditServiceForm {
    private String name;
    private String description;
    private double price;
}