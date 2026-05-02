package com.raghavendra.sga2.controller;

import com.raghavendra.sga2.dto.DepartmentForm;
import com.raghavendra.sga2.dto.EmployeeForm;
import com.raghavendra.sga2.service.DepartmentService;
import com.raghavendra.sga2.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DashboardController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DashboardController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String showDashboard(Model model,
                                @ModelAttribute("departmentForm") DepartmentForm departmentForm,
                                @ModelAttribute("employeeForm") EmployeeForm employeeForm) {
        populateDashboard(model);
        return "index";
    }

    void populateDashboard(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("employeeDepartmentViews", employeeService.getEmployeeDepartmentViews());
    }
}
