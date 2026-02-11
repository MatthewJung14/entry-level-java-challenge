package com.challenge.api.config;

import com.challenge.api.model.EmployeeImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class EmployeeDataLoader {

    // Holds the converted JSON data. 
    private final List<EmployeeImpl> employees;

    public EmployeeDataLoader() {

        // Ensure that ObjectMapper can handle Java 8+ types. 
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        
        try {
            // Load JSON data and deserialize JSON into a List. 
            InputStream is = getClass().getResourceAsStream("/data/employees.json");
            employees = mapper.readValue(is, new TypeReference<List<EmployeeImpl>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load employee mock data", e);
        }
    }

    public List<EmployeeImpl> getEmployees() {
        return employees;
    }
}
