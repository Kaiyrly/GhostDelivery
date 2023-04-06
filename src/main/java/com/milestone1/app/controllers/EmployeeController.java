package com.milestone1.app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.milestone1.app.models.Employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee newEmployee) {
        long id = idCounter.incrementAndGet();
        newEmployee.setId(id);
        employees.add(newEmployee);
        return newEmployee;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = getEmployeeById(id);

        if (existingEmployee != null) {
            updatedEmployee.setId(id);
            int index = employees.indexOf(existingEmployee);
            employees.set(index, updatedEmployee);
            return updatedEmployee;
        } else {
            throw new RuntimeException("Employee not found");
        }
    }
}
