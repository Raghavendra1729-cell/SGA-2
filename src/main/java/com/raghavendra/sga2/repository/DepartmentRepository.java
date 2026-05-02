package com.raghavendra.sga2.repository;

import com.raghavendra.sga2.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
