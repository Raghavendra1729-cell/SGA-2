package com.raghavendra.sga2.service;

import com.raghavendra.sga2.dto.DepartmentForm;
import com.raghavendra.sga2.exception.DuplicateResourceException;
import com.raghavendra.sga2.exception.ResourceNotFoundException;
import com.raghavendra.sga2.model.Department;
import com.raghavendra.sga2.repository.DepartmentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for id " + id));
    }

    public Department createDepartment(DepartmentForm form) {
        Department department = new Department(
                form.getName(),
                form.getLocation(),
                form.getPhoneExtension(),
                form.getEstablishedYear()
        );
        return saveDepartment(department);
    }

    public Department updateDepartment(Long id, DepartmentForm form) {
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setName(form.getName());
        existingDepartment.setLocation(form.getLocation());
        existingDepartment.setPhoneExtension(form.getPhoneExtension());
        existingDepartment.setEstablishedYear(form.getEstablishedYear());
        return saveDepartment(existingDepartment);
    }

    private Department saveDepartment(Department department) {
        try {
            return departmentRepository.save(department);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateResourceException("Department name must be unique.");
        }
    }
}
