package com.kamilsmolarek.autofix.service;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository repository;

    public ServiceServiceImpl(ServiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServiceDTO create(CreateServiceForm form) {
        ServiceDTO service = ServiceDTO.builder()
                .id(UUID.randomUUID().toString())
                .name(form.getName())
                .description(form.getDescription())
                .price(form.getPrice())
                .workshopId(form.getWorkshopId())
                .build();

        return repository.save(service);
    }

    @Override
    public ServiceDTO edit(String id, EditServiceForm form) {
        ServiceDTO service = repository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SERVICE_NOT_FOUND));

        service.setName(form.getName());
        service.setDescription(form.getDescription());
        service.setPrice(form.getPrice());

        return repository.save(service);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<ServiceDTO> listByWorkshopId(String workshopId) {
        return repository.findByWorkshopId(workshopId);
    }

    @Override
    public ServiceDTO get(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.SERVICE_NOT_FOUND));
    }
}
