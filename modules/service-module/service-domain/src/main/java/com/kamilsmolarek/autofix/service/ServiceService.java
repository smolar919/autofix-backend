package com.kamilsmolarek.autofix.service;


import java.util.List;

public interface ServiceService {
    ServiceDTO create(CreateServiceForm form);

    ServiceDTO edit(String id, EditServiceForm form);

    void delete(String id);

    List<ServiceDTO> listByWorkshopId(String workshopId);
}
