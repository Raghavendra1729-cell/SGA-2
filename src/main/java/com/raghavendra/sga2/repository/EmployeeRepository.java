package com.raghavendra.sga2.repository;

import com.raghavendra.sga2.dto.EmployeeDepartmentView;
import com.raghavendra.sga2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
            select new com.raghavendra.sga2.dto.EmployeeDepartmentView(
                e.fullName,
                e.email,
                e.jobTitle,
                d.name,
                d.location
            )
            from Employee e
            inner join e.department d
            order by e.id
            """)
    List<EmployeeDepartmentView> fetchEmployeeDepartmentDetails();
}
