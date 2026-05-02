package com.raghavendra.sga2.controller;

import com.raghavendra.sga2.dto.EmployeeForm;
import com.raghavendra.sga2.exception.DuplicateResourceException;
import com.raghavendra.sga2.model.Employee;
import com.raghavendra.sga2.service.DepartmentService;
import com.raghavendra.sga2.service.EmployeeService;
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
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final DashboardController dashboardController;

    public EmployeeController(EmployeeService employeeService,
                              DepartmentService departmentService,
                              DashboardController dashboardController) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.dashboardController = dashboardController;
    }

    @PostMapping("/employees")
    public String createEmployee(@Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm,
                                 BindingResult bindingResult,
                                 @ModelAttribute("departmentForm") com.raghavendra.sga2.dto.DepartmentForm departmentForm,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            dashboardController.populateDashboard(model);
            return "index";
        }

        try {
            employeeService.createEmployee(employeeForm);
            redirectAttributes.addFlashAttribute("successMessage", "Employee created successfully.");
            return "redirect:/";
        } catch (DuplicateResourceException exception) {
            bindingResult.rejectValue("email", "duplicate", exception.getMessage());
            dashboardController.populateDashboard(model);
            return "index";
        }
    }

    @GetMapping("/employees/{id}/edit")
    public String showUpdateEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(employee.getId());
        employeeForm.setFullName(employee.getFullName());
        employeeForm.setEmail(employee.getEmail());
        employeeForm.setJobTitle(employee.getJobTitle());
        employeeForm.setSalary(employee.getSalary());
        employeeForm.setDepartmentId(employee.getDepartment().getId());
        model.addAttribute("employeeForm", employeeForm);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employee-edit";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable("id") Long id,
                                 @Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employee-edit";
        }

        try {
            employeeService.updateEmployee(id, employeeForm);
            redirectAttributes.addFlashAttribute("successMessage", "Employee updated successfully.");
            return "redirect:/";
        } catch (DuplicateResourceException exception) {
            bindingResult.rejectValue("email", "duplicate", exception.getMessage());
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employee-edit";
        }
    }
}
