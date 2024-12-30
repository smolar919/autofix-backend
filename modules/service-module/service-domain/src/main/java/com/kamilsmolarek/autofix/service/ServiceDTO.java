package com.kamilsmolarek.autofix.service;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ServiceDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private String workshopId;
}
