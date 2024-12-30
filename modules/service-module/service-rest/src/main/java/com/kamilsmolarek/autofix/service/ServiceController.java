package com.kamilsmolarek.autofix.service;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ServiceDTO createService(@RequestBody CreateServiceForm form) {
        return serviceService.create(form);
    }

    @PutMapping("/{id}")
    public ServiceDTO editService(@PathVariable String id, @RequestBody EditServiceForm form) {
        return serviceService.edit(id, form);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable String id) {
        serviceService.delete(id);
    }

    @GetMapping("/workshop/{workshopId}")
    public List<ServiceDTO> listServicesByWorkshopId(@PathVariable String workshopId) {
        return serviceService.listByWorkshopId(workshopId);
    }
}
