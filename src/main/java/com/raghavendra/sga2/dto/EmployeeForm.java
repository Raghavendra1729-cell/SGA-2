package com.raghavendra.sga2.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class EmployeeForm {

    private Long id;

    @NotBlank(message = "Employee name is required.")
    private String fullName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Enter a valid email.")
    private String email;

    @NotBlank(message = "Job title is required.")
    private String jobTitle;

    @NotNull(message = "Salary is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero.")
    private BigDecimal salary;

    @NotNull(message = "Department is required.")
    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
