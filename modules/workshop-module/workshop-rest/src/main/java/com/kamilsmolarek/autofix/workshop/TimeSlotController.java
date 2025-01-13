package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.workshop.forms.CreateTimeSlotForm;
import com.kamilsmolarek.autofix.workshop.forms.EditTimeSlotForm;
import com.kamilsmolarek.autofix.workshop.model.TimeSlot;
import com.kamilsmolarek.autofix.workshop.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workshops/{workshopId}/timeslots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TimeSlot createTimeSlot(@PathVariable String workshopId, @RequestBody CreateTimeSlotForm form) {
        // opcjonalnie sprawdź, czy workshopId z path jest zgodne z form
        return timeSlotService.createTimeSlot(form);
    }

    @PutMapping("/{timeSlotId}")
    public TimeSlot updateTimeSlot(@PathVariable String workshopId, @PathVariable String timeSlotId, @RequestBody EditTimeSlotForm form) {
        // Upewnij się, że form.id jest zgodne z timeSlotId, lub ustaw je
        form.setId(timeSlotId);
        return timeSlotService.updateTimeSlot(form);
    }

    @DeleteMapping("/{timeSlotId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTimeSlot(@PathVariable String workshopId, @PathVariable String timeSlotId) {
        timeSlotService.deleteTimeSlot(timeSlotId);
    }

    @GetMapping
    public List<TimeSlot> getTimeSlots(@PathVariable String workshopId, @RequestParam(value = "employeeId", required = false) String employeeId) {
        if (employeeId != null && !employeeId.isEmpty()) {
            return timeSlotService.getTimeSlotsByEmployee(workshopId, employeeId);
        }
        return timeSlotService.getTimeSlots(workshopId);
    }

    @GetMapping("/{timeSlotId}")
    public TimeSlot getTimeSlot(@PathVariable String workshopId, @PathVariable String timeSlotId) {
        return timeSlotService.getTimeSlot(timeSlotId);
    }
}
