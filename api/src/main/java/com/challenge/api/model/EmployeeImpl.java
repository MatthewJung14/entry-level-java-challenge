package com.challenge.api.model;

import java.time.Instant;
import java.util.UUID;

public class EmployeeImpl implements Employee {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    // Default Constructor.
    public EmployeeImpl() {}

    // Constructor with arguments.
    public EmployeeImpl(
            UUID uuid,
            String firstName,
            String lastName,
            String fullName,
            Integer salary,
            Integer age,
            String jobTitle,
            String email,
            Instant contractHireDate,
            Instant contractTerminationDate) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.email = email;
        this.contractHireDate = contractHireDate;
        this.contractTerminationDate = contractTerminationDate;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }
    ;

    /**
     * Set by either the Service or Data layer.
     * @param uuid required non-null
     */
    @Override
    public void setUuid(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("uuid is required");
        }
        this.uuid = uuid;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public String getFullName() {
        return this.fullName;
    }

    @Override
    public void setFullName(String name) {
        this.fullName = name;
    }

    @Override
    public Integer getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getJobTitle() {
        return this.jobTitle;
    }

    @Override
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Instant getContractHireDate() {
        return this.contractHireDate;
    }

    @Override
    public void setContractHireDate(Instant date) {
        this.contractHireDate = date;
    }

    /**
     * Nullable.
     * @return null, if Employee has not been terminated.
     */
    @Override
    public Instant getContractTerminationDate() {
        return this.contractTerminationDate;
    }

    @Override
    public void setContractTerminationDate(Instant date) {
        this.contractTerminationDate = date;
    }
}
