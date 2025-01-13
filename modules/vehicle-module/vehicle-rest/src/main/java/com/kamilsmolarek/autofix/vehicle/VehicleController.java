package com.kamilsmolarek.autofix.vehicle;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody CreateVehicleForm form) {
        return vehicleService.create(form);
    }

    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable String id) {
        return vehicleService.get(id);
    }

    @PutMapping("/{id}")
    public Vehicle editVehicle(@PathVariable String id, @RequestBody EditVehicleForm form) {
        return vehicleService.edit(id, form);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable String id) {
        vehicleService.delete(id);
    }

    @GetMapping("/owner/{ownerId}")
    public List<Vehicle> listVehiclesByOwnerId(@PathVariable String ownerId) {
        return vehicleService.listByOwnerId(ownerId);
    }
}
