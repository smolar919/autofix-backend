package com.kamilsmolarek.autofix.workshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class AddressEntity {
    @Id
    private String id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "voivodeship")
    private String voivodeship;

    @Column(name = "country")
    private String country;
}
