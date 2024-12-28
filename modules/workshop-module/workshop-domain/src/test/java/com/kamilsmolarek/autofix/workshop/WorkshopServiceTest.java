package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.workshop.forms.CreateWorkshopForm;
import com.kamilsmolarek.autofix.workshop.forms.EditWorkshopForm;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.service.WorkshopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkshopServiceTest {

    private WorkshopServiceImpl workshopService;
    private WorkshopRepositoryMock workshopRepositoryMock;

    @BeforeEach
    void setUp() {
        workshopRepositoryMock = new WorkshopRepositoryMock();
        workshopService = new WorkshopServiceImpl(workshopRepositoryMock);
    }

    @Test
    void testCreateWorkshop() {
        CreateWorkshopForm form = new CreateWorkshopForm("Warsztat 1", "Warszawska", "Plock", "09-400", "mazowieckie", "Polska");
        Workshop workshop = workshopService.create(form);

        assertNotNull(workshop);
        assertEquals("Warsztat 1", workshop.getName());
    }

    @Test
    void testEditWorkshop() {
        Workshop workshop = workshopRepositoryMock.save(new Workshop("1", "Warsztat 1", null, null));

        EditWorkshopForm form = new EditWorkshopForm("Updated Warsztat", "Plock", "Polska", "09-400", "mazowieckie", "Polska");
        Workshop updatedWorkshop = workshopService.edit(workshop.getId(), form);

        assertNotNull(updatedWorkshop);
        assertEquals("Updated Warsztat", updatedWorkshop.getName());
    }

    @Test
    void testDeleteWorkshop() {
        Workshop workshop = workshopRepositoryMock.save(new Workshop("1", "Warsztat 1", null, null));

        workshopService.delete(workshop.getId());

        assertFalse(workshopRepositoryMock.findById(workshop.getId()).isPresent());
    }
}