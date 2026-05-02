package com.raghavendra.sga2.service;

import com.raghavendra.sga2.dto.EmployeeDepartmentView;
import com.raghavendra.sga2.dto.EmployeeForm;
import com.raghavendra.sga2.exception.DuplicateResourceException;
import com.raghavendra.sga2.exception.ResourceNotFoundException;
import com.raghavendra.sga2.model.Department;
import com.raghavendra.sga2.model.Employee;
import com.raghavendra.sga2.repository.EmployeeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id " + id));
    }

    public List<EmployeeDepartmentView> getEmployeeDepartmentViews() {
        return employeeRepository.fetchEmployeeDepartmentDetails();
    }

    public Employee createEmployee(EmployeeForm form) {
        Department department = departmentService.getDepartmentById(form.getDepartmentId());
        Employee employee = new Employee(
                form.getFullName(),
                form.getEmail(),
                form.getJobTitle(),
                form.getSalary(),
                department
        );
        return saveEmployee(employee);
    }

    public Employee updateEmployee(Long id, EmployeeForm form) {
        Department department = departmentService.getDepartmentById(form.getDepartmentId());
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setFullName(form.getFullName());
        existingEmployee.setEmail(form.getEmail());
        existingEmployee.setJobTitle(form.getJobTitle());
        existingEmployee.setSalary(form.getSalary());
        existingEmployee.setDepartment(department);
        return saveEmployee(existingEmployee);
    }

    private Employee saveEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateResourceException("Employee email must be unique.");
        }
    }
}
