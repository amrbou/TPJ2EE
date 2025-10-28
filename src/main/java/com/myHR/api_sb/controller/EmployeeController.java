package com.myHR.api_sb.controller;
import com.myHR.api_sb.configurations.ApplicationPropertiesConfiguration;
import com.myHR.api_sb.repository.EmployeeRepository;
import com.myHR.api_sb.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RefreshScope
@RestController
public class EmployeeController implements HealthIndicator {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ApplicationPropertiesConfiguration appProperties;

    // 🔹 Liste de tous les employés
    @GetMapping("/employees")
    public List<Employee> listEmployees() {
        System.out.println("********* EmployeeController listEmployees() ");
        List<Employee> employees = employeeRepository.findAll();

        int limit = appProperties.getLimitDEmploye();
        List<Employee> limitedList = employees.subList(0, Math.min(limit, employees.size()));
        return limitedList;
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        System.out.println("********* EmployeeController getEmployeeById(" + id + ") ");
        Optional<Employee> employee = employeeRepository.findById(id);

        return employee;
    }

    @Override
    public Health health() {
        System.out.println("****** Actuator : EmployeeController health() ");
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return Health.down().withDetail("error", "Aucun employé disponible").build();
        }
        return Health.up().withDetail("count", employees.size()).build();
    }
}
