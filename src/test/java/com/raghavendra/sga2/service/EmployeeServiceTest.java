package com.raghavendra.sga2.service;

import com.raghavendra.sga2.dto.EmployeeForm;
import com.raghavendra.sga2.exception.DuplicateResourceException;
import com.raghavendra.sga2.exception.ResourceNotFoundException;
import com.raghavendra.sga2.model.Department;
import com.raghavendra.sga2.model.Employee;
import com.raghavendra.sga2.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployeeSavesEmployee() {
        Department department = new Department("Engineering", "Bengaluru", "ENG-101", 2010);
        EmployeeForm form = new EmployeeForm();
        form.setFullName("Aarav Sharma");
        form.setEmail("aarav.sharma@company.com");
        form.setJobTitle("Software Engineer");
        form.setSalary(new BigDecimal("65000"));
        form.setDepartmentId(1L);

        when(departmentService.getDepartmentById(1L)).thenReturn(department);
        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Employee saved = employeeService.createEmployee(form);

        assertThat(saved.getDepartment().getName()).isEqualTo("Engineering");
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void updateEmployeeThrowsWhenMissing() {
        EmployeeForm form = new EmployeeForm();
        form.setDepartmentId(1L);
        when(departmentService.getDepartmentById(1L))
                .thenReturn(new Department("Engineering", "Bengaluru", "ENG-101", 2010));
        when(employeeRepository.findById(55L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(55L, form))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Employee not found");
    }

    @Test
    void createEmployeeWrapsIntegrityViolation() {
        Department department = new Department("Engineering", "Bengaluru", "ENG-101", 2010);
        EmployeeForm form = new EmployeeForm();
        form.setFullName("Aarav Sharma");
        form.setEmail("aarav.sharma@company.com");
        form.setJobTitle("Software Engineer");
        form.setSalary(new BigDecimal("65000"));
        form.setDepartmentId(1L);

        when(departmentService.getDepartmentById(1L)).thenReturn(department);
        when(employeeRepository.save(any(Employee.class)))
                .thenThrow(new DataIntegrityViolationException("duplicate"));

        assertThatThrownBy(() -> employeeService.createEmployee(form))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("unique");
    }
}
