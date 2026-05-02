package com.raghavendra.sga2.service;

import com.raghavendra.sga2.dto.DepartmentForm;
import com.raghavendra.sga2.exception.DuplicateResourceException;
import com.raghavendra.sga2.exception.ResourceNotFoundException;
import com.raghavendra.sga2.model.Department;
import com.raghavendra.sga2.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void createDepartmentSavesDepartment() {
        DepartmentForm form = new DepartmentForm();
        form.setName("Engineering");
        form.setLocation("Bengaluru");
        form.setPhoneExtension("ENG-101");
        form.setEstablishedYear(2010);

        when(departmentRepository.save(any(Department.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Department saved = departmentService.createDepartment(form);

        assertThat(saved.getName()).isEqualTo("Engineering");
        verify(departmentRepository).save(any(Department.class));
    }

    @Test
    void updateDepartmentThrowsWhenMissing() {
        DepartmentForm form = new DepartmentForm();
        when(departmentRepository.findById(77L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.updateDepartment(77L, form))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Department not found");
    }

    @Test
    void createDepartmentWrapsIntegrityViolation() {
        DepartmentForm form = new DepartmentForm();
        form.setName("Engineering");
        form.setLocation("Bengaluru");
        form.setPhoneExtension("ENG-101");
        form.setEstablishedYear(2010);

        when(departmentRepository.save(any(Department.class)))
                .thenThrow(new DataIntegrityViolationException("duplicate"));

        assertThatThrownBy(() -> departmentService.createDepartment(form))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("unique");
    }
}
