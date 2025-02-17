package com.kamilsmolarek.autofix.workshop.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkshopImage {
    private String id;
    private String workshopId;
    private String imagePath;
    private String fileName;
}
