package com.kamilsmolarek.autofix.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateServiceForm {
    private String name;
    private String description;
    private double price;
    private String workshopId;
}
