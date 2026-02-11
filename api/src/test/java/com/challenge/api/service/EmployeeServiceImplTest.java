package com.challenge.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.challenge.api.config.EmployeeDataLoader;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;
    private EmployeeDataLoader mockLoader;

    @BeforeEach
    void setUp() {
        mockLoader = mock(EmployeeDataLoader.class);

        EmployeeImpl employee = new EmployeeImpl(
                UUID.fromString("f31ea0fb-0c9b-4b11-9585-b542392712ce"),
                "Brenda",
                "Margquez",
                "Brenda Margquez",
                90000,
                20,
                "Software Engineer",
                "brenda.margquez@example.com",
                Instant.now(),
                null);

        // Employee is returned when getEmployees is called.
        when(mockLoader.getEmployees()).thenReturn(List.of(employee));

        employeeService = new EmployeeServiceImpl(mockLoader);
        employeeService = new EmployeeServiceImpl(mockLoader);
    }

    @Test
    void testGetAllEmployees_Success() {
        List<Employee> allEmployees = employeeService.getAllEmployees();

        // Expect only 1 employee.
        assertEquals(1, allEmployees.size(), "Should return exactly 1 employee");

        Employee emp = allEmployees.get(0);

        assertEquals("Brenda", emp.getFirstName());
        assertEquals("Margquez", emp.getLastName());
        assertEquals(UUID.fromString("f31ea0fb-0c9b-4b11-9585-b542392712ce"), emp.getUuid());
    }

    @Test
    void testGetEmployeeByUuid_Found() {
        UUID uuid = UUID.fromString("f31ea0fb-0c9b-4b11-9585-b542392712ce");

        Employee result = employeeService.getEmployeeByUuid(uuid);

        assertNotNull(result);
        assertEquals("Brenda", result.getFirstName());
    }

    @Test
    void testGetEmployeeByUuid_NotFound() {
        UUID uuid = UUID.fromString("d1ec13cd-c318-49ee-9cf2-60b4ccf72ec1");

        Employee result = employeeService.getEmployeeByUuid(uuid);

        assertNull(result);
    }

    @Test
    void testCreateEmployee_Success() {
        EmployeeImpl newEmp = new EmployeeImpl();
        newEmp.setFirstName("Natalie");
        newEmp.setLastName("Garcia");

        Employee created = employeeService.createEmployee(newEmp);

        assertNotNull(created.getUuid());
        assertEquals("Natalie", created.getFirstName());
    }

    @Test
    void testCreateEmployee_Error() {
        EmployeeImpl newEmp = new EmployeeImpl();
        newEmp.setFirstName("Natalie");
        newEmp.setLastName("Garcia");

        newEmp.setUuid(UUID.randomUUID());

        // Service rejects due to UUID being provided.
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(newEmp));

        assertEquals("Client cannot specify UUID when creating a new employee.", exception.getMessage());
    }
}
