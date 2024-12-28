package com.kamilsmolarek.autofix.workshop.mapper;

import com.kamilsmolarek.autofix.workshop.entity.AddressEntity;
import com.kamilsmolarek.autofix.workshop.model.Address;

public class AddressMapper {

    public static Address toAddress(AddressEntity addressEntity) {
        return Address.builder()
                .id(addressEntity.getId())
                .street(addressEntity.getStreet())
                .city(addressEntity.getCity())
                .postalCode(addressEntity.getPostalCode())
                .voivodeship(addressEntity.getVoivodeship())
                .country(addressEntity.getCountry())
                .build();
    }

    public static AddressEntity toEntity(Address address) {
        return AddressEntity.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .voivodeship(address.getVoivodeship())
                .country(address.getCountry())
                .build();
    }
}
