package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.forms.CreateWorkshopForm;
import com.kamilsmolarek.autofix.workshop.forms.EditWorkshopForm;
import com.kamilsmolarek.autofix.workshop.model.Address;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.repository.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class WorkshopServiceImpl implements WorkshopService{
    private final WorkshopRepository workshopRepository;

    public WorkshopServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Override
    public SearchResponse<Workshop> search(SearchForm form) {
        return workshopRepository.search(form);
    }

    private Workshop getOrThrow(String id) {
        return workshopRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.WORKSHOP_NOT_FOUND));
    }

    @Override
    public Workshop create(CreateWorkshopForm form) {
        Address address = Address.builder()
                .id(UUID.randomUUID().toString())
                .city(form.getCity())
                .country(form.getCountry())
                .voivodeship(form.getVoivodeship())
                .postalCode(form.getPostalCode())
                .street(form.getStreet())
                .build();

        Workshop workshop = Workshop.builder()
                .name(form.getName())
                .address(address)
                .id(UUID.randomUUID().toString())
                .employees(new ArrayList<>())
                .build();
        return workshopRepository.save(workshop);
    }

    @Override
    public Workshop edit(String id, EditWorkshopForm form) {
        Workshop workshop = getOrThrow(id);

        Address address = workshop.getAddress();
        address.setCity(form.getCity());
        address.setCountry(form.getCountry());
        address.setStreet(form.getStreet());
        address.setVoivodeship(form.getVoivodeship());
        address.setPostalCode(form.getPostalCode());

        workshop.setName(form.getName());
        workshop.setAddress(address);
        return workshopRepository.save(workshop);
    }

    @Override
    public void delete(String id) {
        getOrThrow(id);
        workshopRepository.deleteById(id);
    }

}