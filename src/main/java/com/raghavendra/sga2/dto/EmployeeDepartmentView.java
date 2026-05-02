package com.raghavendra.sga2.dto;

public class EmployeeDepartmentView {

    private final String employeeName;
    private final String email;
    private final String jobTitle;
    private final String departmentName;
    private final String departmentLocation;

    public EmployeeDepartmentView(String employeeName, String email, String jobTitle, String departmentName, String departmentLocation) {
        this.employeeName = employeeName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.departmentName = departmentName;
        this.departmentLocation = departmentLocation;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmail() {
        return email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentLocation() {
        return departmentLocation;
    }
}
