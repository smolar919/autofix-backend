package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.forms.CreateWorkshopForm;
import com.kamilsmolarek.autofix.workshop.forms.EditWorkshopForm;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.service.WorkshopService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workshop")
public class WorkshopController {
    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @PostMapping("/search")
    public SearchResponse<Workshop> search(@RequestBody SearchForm form) {
        return workshopService.search(form);
    }

    @PostMapping
    public Workshop create(@RequestBody CreateWorkshopForm form) {
        return workshopService.create(form);
    }

    @PutMapping("/{id}")
    public Workshop edit(@PathVariable String id, @RequestBody EditWorkshopForm form) {
        return workshopService.edit(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        workshopService.delete(id);
    }
}
