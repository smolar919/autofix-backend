package com.kamilsmolarek.autofix.workshop.entity;

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

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "is_visible")
    private boolean isVisible;
}
