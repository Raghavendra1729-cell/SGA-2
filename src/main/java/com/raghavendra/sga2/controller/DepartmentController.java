package com.raghavendra.sga2.controller;

import com.raghavendra.sga2.dto.DepartmentForm;
import com.raghavendra.sga2.exception.DuplicateResourceException;
import com.raghavendra.sga2.model.Department;
import com.raghavendra.sga2.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DashboardController dashboardController;

    public DepartmentController(DepartmentService departmentService, DashboardController dashboardController) {
        this.departmentService = departmentService;
        this.dashboardController = dashboardController;
    }

    @PostMapping("/departments")
    public String createDepartment(@Valid @ModelAttribute("departmentForm") DepartmentForm departmentForm,
                                   BindingResult bindingResult,
                                   @ModelAttribute("employeeForm") com.raghavendra.sga2.dto.EmployeeForm employeeForm,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            dashboardController.populateDashboard(model);
            return "index";
        }

        try {
            departmentService.createDepartment(departmentForm);
            redirectAttributes.addFlashAttribute("successMessage", "Department created successfully.");
            return "redirect:/";
        } catch (DuplicateResourceException exception) {
            bindingResult.rejectValue("name", "duplicate", exception.getMessage());
            dashboardController.populateDashboard(model);
            return "index";
        }
    }

    @GetMapping("/departments/{id}/edit")
    public String showUpdateDepartment(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setId(department.getId());
        departmentForm.setName(department.getName());
        departmentForm.setLocation(department.getLocation());
        departmentForm.setPhoneExtension(department.getPhoneExtension());
        departmentForm.setEstablishedYear(department.getEstablishedYear());
        model.addAttribute("departmentForm", departmentForm);
        return "department-edit";
    }

    @PostMapping("/departments/{id}")
    public String updateDepartment(@PathVariable("id") Long id,
                                   @Valid @ModelAttribute("departmentForm") DepartmentForm departmentForm,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "department-edit";
        }

        try {
            departmentService.updateDepartment(id, departmentForm);
            redirectAttributes.addFlashAttribute("successMessage", "Department updated successfully.");
            return "redirect:/";
        } catch (DuplicateResourceException exception) {
            bindingResult.rejectValue("name", "duplicate", exception.getMessage());
            return "department-edit";
        }
    }
}
