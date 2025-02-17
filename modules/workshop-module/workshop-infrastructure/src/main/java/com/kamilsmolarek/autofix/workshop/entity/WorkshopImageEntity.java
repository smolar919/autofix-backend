package com.kamilsmolarek.autofix.workshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "workshop_images")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopImageEntity {
    @Id
    private String id;
    private String workshopId;
    private String imagePath;
    private String fileName;
}