package com.kamilsmolarek.autofix.workshop.entity;

import com.kamilsmolarek.autofix.workshop.model.OpeningHours;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "workshop", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkshopEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "is_visible", nullable = false)
    private boolean isVisible;

    @Column(name = "description")
    private String description;

    @Column(name = "service_ids")
    private String serviceIds;

    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL)
    private List<OpeningHoursEntity> openingHours;
}
