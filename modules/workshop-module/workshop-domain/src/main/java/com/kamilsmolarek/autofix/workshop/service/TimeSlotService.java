package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.workshop.forms.CreateTimeSlotForm;
import com.kamilsmolarek.autofix.workshop.forms.EditTimeSlotForm;
import com.kamilsmolarek.autofix.workshop.model.TimeSlot;

import java.util.List;

public interface TimeSlotService {
    TimeSlot createTimeSlot(CreateTimeSlotForm form);
    TimeSlot updateTimeSlot(EditTimeSlotForm form);
    void deleteTimeSlot(String timeSlotId);
    List<TimeSlot> getTimeSlots(String workshopId);
    TimeSlot getTimeSlot(String timeSlotId);
}
