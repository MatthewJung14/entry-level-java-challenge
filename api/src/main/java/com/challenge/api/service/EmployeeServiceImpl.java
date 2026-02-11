package com.challenge.api.service;

import com.challenge.api.config.EmployeeDataLoader;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // I used a map for key-value pairing.
    private final Map<UUID, Employee> employees = new HashMap<>();

    // Inject Data Loader into constructor to obtain mock data.
    public EmployeeServiceImpl(EmployeeDataLoader loader) {
        for (EmployeeImpl emp : loader.getEmployees()) {
            employees.put(emp.getUuid(), emp);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        // Convert map into list.
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee getEmployeeByUuid(UUID uuid) {
        return employees.get(uuid);
    }

    @Override
    public Employee createEmployee(Employee requestBody) {
        if (requestBody == null) {
            return null;
        }

        // If the client tries to provide a UUID, reject it.
        if (requestBody.getUuid() != null) {
            throw new IllegalArgumentException("Client cannot specify UUID when creating a new employee.");
        }

        requestBody.setUuid(UUID.randomUUID());

        employees.put(requestBody.getUuid(), requestBody);
        return requestBody;
    }
}
