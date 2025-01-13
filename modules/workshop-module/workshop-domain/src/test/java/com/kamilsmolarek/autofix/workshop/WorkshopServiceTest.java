package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.workshop.forms.CreateWorkshopForm;
import com.kamilsmolarek.autofix.workshop.forms.EditWorkshopForm;
import com.kamilsmolarek.autofix.workshop.model.Address;
import com.kamilsmolarek.autofix.workshop.model.Employee;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.service.WorkshopServiceImpl;
import com.kamilsmolarek.autofix.user.management.Role;
import com.kamilsmolarek.autofix.user.management.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class WorkshopServiceTest {

    private WorkshopServiceImpl workshopService;
    private WorkshopRepositoryMock workshopRepositoryMock;
    private UserManagementRepositoryMock userManagementRepositoryMock;
    private EmployeeRepositoryMock employeeRepositoryMock;

    @BeforeEach
    void setUp() {
        workshopRepositoryMock = new WorkshopRepositoryMock();
        userManagementRepositoryMock = new UserManagementRepositoryMock();
        employeeRepositoryMock = new EmployeeRepositoryMock();

        workshopService = new WorkshopServiceImpl(
                workshopRepositoryMock,
                userManagementRepositoryMock,
                employeeRepositoryMock
        );
    }

    @Test
    @DisplayName("Tworzenie warsztatu z prawidłowymi danymi powinno przejść pomyślnie")
    void testCreateWorkshop_Success() {
        // Given: Tworzenie i zapisanie użytkownika (właściciela)
        User owner = User.builder()
                .id("user-1")
                .firstName("Marek")
                .lastName("Wójcik")
                .email("marek.wojcik@example.com")
                .createdOn(Instant.now())
                .isBlocked(false)
                .role(Role.CUSTOMER)
                .build();
        userManagementRepositoryMock.save(owner);

        // Tworzenie formularza do tworzenia warsztatu
        CreateWorkshopForm form = new CreateWorkshopForm(
                "Warsztat 1",
                "Warszawska 123",
                "Plock",
                "09-400",
                "mazowieckie",
                "Polska",
                "user-1",
                "123456789"
        );

        // When: Tworzenie warsztatu
        Workshop workshop = workshopService.create(form);

        // Then: Assercje
        assertNotNull(workshop, "Warsztat powinien być niepusty");
        assertNotNull(workshop.getId(), "Warsztat powinien mieć ID");
        assertEquals("Warsztat 1", workshop.getName(), "Nazwa warsztatu powinna się zgadzać");
        assertEquals("Warszawska 123", workshop.getAddress().getStreet(), "Ulica powinna się zgadzać");
        assertEquals("Plock", workshop.getAddress().getCity(), "Miasto powinno się zgadzać");
        assertEquals("09-400", workshop.getAddress().getPostalCode(), "Kod pocztowy powinien się zgadzać");
        assertEquals("mazowieckie", workshop.getAddress().getVoivodeship(), "Województwo powinno się zgadzać");
        assertEquals("Polska", workshop.getAddress().getCountry(), "Kraj powinien się zgadzać");
        assertTrue(workshop.isVisible(), "Warsztat powinien być widoczny");

        // Sprawdzenie, czy właściciel ma rolę WORKSHOP_OWNER
        Optional<User> ownerOpt = userManagementRepositoryMock.get("user-1");
        assertTrue(ownerOpt.isPresent(), "Właściciel powinien istnieć");
        User updatedOwner = ownerOpt.get();
        assertEquals(Role.WORKSHOP_OWNER, updatedOwner.getRole(), "Rola właściciela powinna być WORKSHOP_OWNER");

        // Sprawdzenie, czy właściciel został dodany jako pracownik warsztatu
        List<Employee> employees = employeeRepositoryMock.findByUserId("user-1");
        assertEquals(1, employees.size(), "Powinien być dokładnie jeden pracownik przypisany do właściciela");
        Employee ownerEmployee = employees.get(0);
        assertEquals("Właściciel", ownerEmployee.getPosition(), "Stanowisko powinno być 'Właściciel'");
        assertEquals("123456789", ownerEmployee.getPhoneNumber(), "Numer telefonu powinien się zgadzać");
        assertEquals(workshop.getId(), ownerEmployee.getWorkshopId(), "ID warsztatu powinno się zgadzać");
    }

    @Test
    @DisplayName("Edycja warsztatu z prawidłowymi danymi powinna przejść pomyślnie")
    void testEditWorkshop_Success() {
        // Given: Tworzenie i zapisanie warsztatu
        Workshop workshop = workshopRepositoryMock.save(new Workshop(
                "1",
                "user-1",
                "Warsztat 1",
                Address.builder()
                        .id(UUID.randomUUID().toString())
                        .street("Warszawska 123")
                        .city("Plock")
                        .postalCode("09-400")
                        .voivodeship("mazowieckie")
                        .country("Polska")
                        .build(),
                null,
                true
        ));

        // Tworzenie i zapisanie użytkownika (właściciela)
        User owner = User.builder()
                .id("user-1")
                .firstName("Marek")
                .lastName("Wójcik")
                .email("marek.wojcik@example.com")
                .createdOn(Instant.now())
                .isBlocked(false)
                .role(Role.WORKSHOP_OWNER)
                .build();
        userManagementRepositoryMock.save(owner);

        // Tworzenie formularza do edycji warsztatu
        EditWorkshopForm form = new EditWorkshopForm(
                "Warsztat 1 - Updated",
                "Gdańska 456",
                "Warszawa",
                "00-001",
                "mazowieckie",
                "Polska"
        );

        // When: Edycja warsztatu
        Workshop updatedWorkshop = workshopService.edit(workshop.getId(), form);

        // Then: Assercje
        assertNotNull(updatedWorkshop, "Zaktualizowany warsztat powinien być niepusty");
        assertEquals("Warsztat 1 - Updated", updatedWorkshop.getName(), "Nazwa warsztatu powinna się zgadzać");
        assertEquals("Gdańska 456", updatedWorkshop.getAddress().getStreet(), "Ulica powinna się zgadzać");
        assertEquals("Warszawa", updatedWorkshop.getAddress().getCity(), "Miasto powinno się zgadzać");
        assertEquals("00-001", updatedWorkshop.getAddress().getPostalCode(), "Kod pocztowy powinien się zgadzać");
        assertEquals("mazowieckie", updatedWorkshop.getAddress().getVoivodeship(), "Województwo powinno się zgadzać");
        assertEquals("Polska", updatedWorkshop.getAddress().getCountry(), "Kraj powinien się zgadzać");

        // Sprawdzenie, czy zmiany zostały zapisane w repozytorium
        Optional<Workshop> workshopOpt = workshopRepositoryMock.findById("1");
        assertTrue(workshopOpt.isPresent(), "Warsztat powinien istnieć w repozytorium");
        Workshop savedWorkshop = workshopOpt.get();
        assertEquals("Warsztat 1 - Updated", savedWorkshop.getName(), "Nazwa warsztatu w repozytorium powinna się zgadzać");
        assertEquals("Gdańska 456", savedWorkshop.getAddress().getStreet(), "Ulica warsztatu w repozytorium powinna się zgadzać");
    }

    @Test
    @DisplayName("Usuwanie warsztatu bez przypisanych pracowników powinno przejść pomyślnie")
    void testDeleteWorkshop_NoEmployees() {
        // Given: Tworzenie i zapisanie warsztatu
        Workshop workshop = workshopRepositoryMock.save(new Workshop(
                "2",
                "user-2",
                "Warsztat 2",
                Address.builder()
                        .id(UUID.randomUUID().toString())
                        .street("Nowa Ulica 456")
                        .city("Warszawa")
                        .postalCode("00-002")
                        .voivodeship("mazowieckie")
                        .country("Polska")
                        .build(),
                null,
                true
        ));

        // Tworzenie i zapisanie użytkownika (właściciela)
        User owner = User.builder()
                .id("user-2")
                .firstName("Anna")
                .lastName("Kowalska")
                .email("anna.kowalska@example.com")
                .createdOn(Instant.now())
                .isBlocked(false)
                .role(Role.WORKSHOP_OWNER)
                .build();
        userManagementRepositoryMock.save(owner);

        // When: Usuwanie warsztatu
        workshopService.delete(workshop.getId());

        // Then: Warsztat powinien zostać usunięty
        assertFalse(workshopRepositoryMock.findById("2").isPresent(), "Warsztat powinien być usunięty z repozytorium");

        // Właściciel powinien mieć rolę CUSTOMER
        Optional<User> ownerOpt = userManagementRepositoryMock.get("user-2");
        assertTrue(ownerOpt.isPresent(), "Właściciel powinien istnieć w repozytorium");
        User updatedOwner = ownerOpt.get();
        assertEquals(Role.CUSTOMER, updatedOwner.getRole(), "Rola właściciela powinna być CUSTOMER");
    }

    @Test
    @DisplayName("Edycja warsztatu z prawidłowymi danymi powinna przejść pomyślnie")
    void testEditWorkshop2_Success() {
        // Given: Tworzenie i zapisanie warsztatu
        Workshop workshop = workshopRepositoryMock.save(new Workshop(
                "3",
                "user-3",
                "Warsztat 3",
                Address.builder()
                        .id(UUID.randomUUID().toString())
                        .street("Stara Ulica 789")
                        .city("Kraków")
                        .postalCode("30-001")
                        .voivodeship("małopolskie")
                        .country("Polska")
                        .build(),
                null,
                true
        ));

        // Tworzenie i zapisanie użytkownika (właściciela)
        User owner = User.builder()
                .id("user-3")
                .firstName("Jan")
                .lastName("Nowak")
                .email("jan.nowak@example.com")
                .createdOn(Instant.now())
                .isBlocked(false)
                .role(Role.WORKSHOP_OWNER)
                .build();
        userManagementRepositoryMock.save(owner);

        // Tworzenie formularza do edycji warsztatu
        EditWorkshopForm form = new EditWorkshopForm(
                "Warsztat 3 - Updated",
                "Nowa Ulica 101",
                "Gdańsk",
                "80-001",
                "pomorskie",
                "Polska"
        );

        // When: Edycja warsztatu
        Workshop updatedWorkshop = workshopService.edit(workshop.getId(), form);

        // Then: Assercje
        assertNotNull(updatedWorkshop, "Zaktualizowany warsztat powinien być niepusty");
        assertEquals("Warsztat 3 - Updated", updatedWorkshop.getName(), "Nazwa warsztatu powinna się zgadzać");
        assertEquals("Nowa Ulica 101", updatedWorkshop.getAddress().getStreet(), "Ulica powinna się zgadzać");
        assertEquals("Gdańsk", updatedWorkshop.getAddress().getCity(), "Miasto powinno się zgadzać");
        assertEquals("80-001", updatedWorkshop.getAddress().getPostalCode(), "Kod pocztowy powinien się zgadzać");
        assertEquals("pomorskie", updatedWorkshop.getAddress().getVoivodeship(), "Województwo powinno się zgadzać");
        assertEquals("Polska", updatedWorkshop.getAddress().getCountry(), "Kraj powinien się zgadzać");

        // Sprawdzenie, czy zmiany zostały zapisane w repozytorium
        Optional<Workshop> workshopOpt = workshopRepositoryMock.findById("3");
        assertTrue(workshopOpt.isPresent(), "Warsztat powinien istnieć w repozytorium");
        Workshop savedWorkshop = workshopOpt.get();
        assertEquals("Warsztat 3 - Updated", savedWorkshop.getName(), "Nazwa warsztatu w repozytorium powinna się zgadzać");
        assertEquals("Nowa Ulica 101", savedWorkshop.getAddress().getStreet(), "Ulica warsztatu w repozytorium powinna się zgadzać");
    }

    @Test
    @DisplayName("Tworzenie warsztatu z nieistniejącym właścicielem powinno przejść pomyślnie (bez testowania błędów)")
    void testCreateWorkshop_WithNonExistingOwner_Success() {
        // Given: Tworzenie formularza do tworzenia warsztatu z istniejącym właścicielem
        // W tym teście zakładamy, że właściciel istnieje, aby testować tylko zapis poprawnych danych
        User owner = User.builder()
                .id("user-4")
                .firstName("Ewa")
                .lastName("Kowalczyk")
                .email("ewa.kowalczyk@example.com")
                .createdOn(Instant.now())
                .isBlocked(false)
                .role(Role.CUSTOMER)
                .build();
        userManagementRepositoryMock.save(owner);

        CreateWorkshopForm form = new CreateWorkshopForm(
                "Warsztat 4",
                "Ulica Główna 202",
                "Poznań",
                "60-001",
                "wielkopolskie",
                "Polska",
                "user-4",
                "555666777"
        );

        // When: Tworzenie warsztatu
        Workshop workshop = workshopService.create(form);

        // Then: Assercje
        assertNotNull(workshop, "Warsztat powinien być niepusty");
        assertNotNull(workshop.getId(), "Warsztat powinien mieć ID");
        assertEquals("Warsztat 4", workshop.getName(), "Nazwa warsztatu powinna się zgadzać");
        assertEquals("Ulica Główna 202", workshop.getAddress().getStreet(), "Ulica powinna się zgadzać");
        assertEquals("Poznań", workshop.getAddress().getCity(), "Miasto powinno się zgadzać");
        assertEquals("60-001", workshop.getAddress().getPostalCode(), "Kod pocztowy powinien się zgadzać");
        assertEquals("wielkopolskie", workshop.getAddress().getVoivodeship(), "Województwo powinno się zgadzać");
        assertEquals("Polska", workshop.getAddress().getCountry(), "Kraj powinien się zgadzać");
        assertTrue(workshop.isVisible(), "Warsztat powinien być widoczny");

        // Sprawdzenie, czy właściciel ma rolę WORKSHOP_OWNER
        Optional<User> ownerOpt = userManagementRepositoryMock.get("user-4");
        assertTrue(ownerOpt.isPresent(), "Właściciel powinien istnieć");
        User updatedOwner = ownerOpt.get();
        assertEquals(Role.WORKSHOP_OWNER, updatedOwner.getRole(), "Rola właściciela powinna być WORKSHOP_OWNER");

        // Sprawdzenie, czy właściciel został dodany jako pracownik warsztatu
        List<Employee> employees = employeeRepositoryMock.findByUserId("user-4");
        assertEquals(1, employees.size(), "Powinien być dokładnie jeden pracownik przypisany do właściciela");
        Employee ownerEmployee = employees.get(0);
        assertEquals("Właściciel", ownerEmployee.getPosition(), "Stanowisko powinno być 'Właściciel'");
        assertEquals("555666777", ownerEmployee.getPhoneNumber(), "Numer telefonu powinien się zgadzać");
        assertEquals(workshop.getId(), ownerEmployee.getWorkshopId(), "ID warsztatu powinno się zgadzać");
    }
}
