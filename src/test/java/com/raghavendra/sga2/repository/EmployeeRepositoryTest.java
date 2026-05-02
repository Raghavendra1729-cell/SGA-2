package com.raghavendra.sga2.repository;

import com.raghavendra.sga2.dto.EmployeeDepartmentView;
import com.raghavendra.sga2.model.Department;
import com.raghavendra.sga2.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void fetchEmployeeDepartmentDetailsReturnsJoinedRows() {
        Department department = departmentRepository.save(new Department("QA", "Mysuru", "QA-111", 2020));
        employeeRepository.save(new Employee(
                "Ananya Rao",
                "ananya.rao@company.com",
                "QA Engineer",
                new BigDecimal("45000"),
                department
        ));

        List<EmployeeDepartmentView> rows = employeeRepository.fetchEmployeeDepartmentDetails();

        assertThat(rows)
                .extracting(EmployeeDepartmentView::getEmployeeName, EmployeeDepartmentView::getDepartmentName, EmployeeDepartmentView::getDepartmentLocation)
                .contains(org.assertj.core.api.Assertions.tuple("Ananya Rao", "QA", "Mysuru"));
    }
}
