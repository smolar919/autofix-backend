package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.commons.errors.ApplicationException;
import com.kamilsmolarek.autofix.commons.errors.ErrorCode;
import com.kamilsmolarek.autofix.workshop.forms.CreateTimeSlotForm;
import com.kamilsmolarek.autofix.workshop.forms.EditTimeSlotForm;
import com.kamilsmolarek.autofix.workshop.model.TimeSlot;
import com.kamilsmolarek.autofix.workshop.model.TimeSlotStatus;
import com.kamilsmolarek.autofix.workshop.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }


    @Override
    public TimeSlot createTimeSlot(CreateTimeSlotForm form) {
        TimeSlot timeSlot = new TimeSlot(
                UUID.randomUUID().toString(),
                form.getWorkshopId(),
                form.getEmployeeId(),
                form.getStartDateTime(),
                form.getEndDateTime(),
                TimeSlotStatus.AVAILABLE
        );
        return timeSlotRepository.save(timeSlot);
    }

    @Override
    public TimeSlot updateTimeSlot(EditTimeSlotForm form) {
        TimeSlot timeSlot = timeSlotRepository.findById(form.getId())
                .orElseThrow(() -> new RuntimeException("TimeSlot not found with id: " + form.getId()));
        timeSlot.setStartDateTime(form.getStartDateTime());
        timeSlot.setEndDateTime(form.getEndDateTime());
        timeSlot.setStatus(form.getStatus());
        timeSlot.setEmployeeId(form.getEmployeeId());
        return timeSlotRepository.save(timeSlot);
    }

    @Override
    public void deleteTimeSlot(String timeSlotId) {
        timeSlotRepository.deleteById(timeSlotId);
    }

    @Override
    public List<TimeSlot> getTimeSlots(String workshopId) {
        return timeSlotRepository.findByWorkshopId(workshopId);
    }

    @Override
    public List<TimeSlot> getTimeSlotsByEmployee(String workshopId, String employeeId) {
        return timeSlotRepository.findByWorkshopIdAndEmployeeId(workshopId, employeeId);
    }

    @Override
    public TimeSlot getTimeSlot(String timeSlotId) {
        return timeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.TIME_SLOT_NOT_FOUND));
    }
}