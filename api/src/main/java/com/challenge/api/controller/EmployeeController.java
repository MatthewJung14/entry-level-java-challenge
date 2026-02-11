package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.service.EmployeeService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Inject Service layer into the Controller layer.
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee models as necessary.
     * @return One or more Employees.
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable String uuid) {

        // If a valid UUID is not passed, Spring throws an error before
        // this method is called. To prevent this, I made the parameter a string!
        UUID parsedUuid;
        try {
            parsedUuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID format.");
        }

        Employee employee = employeeService.getEmployeeByUuid(parsedUuid);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found.");
        }
        return employee;
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param requestBody hint!
     * @return Newly created Employee
     */
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeImpl requestBody) {
        Employee created = employeeService.createEmployee(requestBody);
        if (created == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid employee payload");
        }
        return created;
    }
}
