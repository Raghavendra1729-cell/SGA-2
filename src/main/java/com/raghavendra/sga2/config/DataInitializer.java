package com.raghavendra.sga2.config;

import com.raghavendra.sga2.model.Department;
import com.raghavendra.sga2.model.Employee;
import com.raghavendra.sga2.repository.DepartmentRepository;
import com.raghavendra.sga2.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadSampleData(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        return args -> {
            if (departmentRepository.count() > 0 || employeeRepository.count() > 0) {
                return;
            }

            List<Department> departments = departmentRepository.saveAll(List.of(
                    new Department("Engineering", "Bengaluru", "ENG-101", 2010),
                    new Department("Human Resources", "Hyderabad", "HR-102", 2011),
                    new Department("Finance", "Mumbai", "FIN-103", 2012),
                    new Department("Marketing", "Delhi", "MKT-104", 2013),
                    new Department("Sales", "Chennai", "SAL-105", 2014),
                    new Department("Operations", "Pune", "OPS-106", 2015),
                    new Department("Legal", "Kolkata", "LEG-107", 2016),
                    new Department("Support", "Noida", "SUP-108", 2017),
                    new Department("Research", "Ahmedabad", "RES-109", 2018),
                    new Department("Administration", "Jaipur", "ADM-110", 2019)
            ));

            employeeRepository.saveAll(List.of(
                    new Employee("Aarav Sharma", "aarav.sharma@company.com", "Software Engineer", new BigDecimal("65000"), departments.get(0)),
                    new Employee("Ishita Verma", "ishita.verma@company.com", "HR Specialist", new BigDecimal("52000"), departments.get(1)),
                    new Employee("Rohan Mehta", "rohan.mehta@company.com", "Financial Analyst", new BigDecimal("58000"), departments.get(2)),
                    new Employee("Neha Kapoor", "neha.kapoor@company.com", "Marketing Executive", new BigDecimal("54000"), departments.get(3)),
                    new Employee("Karan Malhotra", "karan.malhotra@company.com", "Sales Manager", new BigDecimal("70000"), departments.get(4)),
                    new Employee("Pooja Iyer", "pooja.iyer@company.com", "Operations Lead", new BigDecimal("68000"), departments.get(5)),
                    new Employee("Rahul Nair", "rahul.nair@company.com", "Legal Advisor", new BigDecimal("72000"), departments.get(6)),
                    new Employee("Sana Khan", "sana.khan@company.com", "Support Engineer", new BigDecimal("50000"), departments.get(7)),
                    new Employee("Vikram Joshi", "vikram.joshi@company.com", "Research Associate", new BigDecimal("61000"), departments.get(8)),
                    new Employee("Meera Reddy", "meera.reddy@company.com", "Admin Officer", new BigDecimal("49000"), departments.get(9))
            ));
        };
    }
}
