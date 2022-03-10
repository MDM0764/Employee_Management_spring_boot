package com.mdm.employee.controller;


import com.mdm.employee.entities.Employee;
import com.mdm.employee.repositories.employeeRepository;
import com.mdm.employee.service.employeeService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private employeeService service;

    @GetMapping("/employees")
    public Page<Employee> showAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "empId") String sortBy,
            @RequestParam(defaultValue = "empId") String sortDirection) {
        return service.getAllEmployees(pageNo, pageSize, sortBy, sortDirection);
    }

    @PostMapping(value="/employee", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void save(Employee employee, HttpServletRequest req, HttpServletResponse response) throws IOException {
        service.saveEmployee(employee, req, response);
    }

    @PutMapping(value = "/employee/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(Employee employee, @PathVariable Integer id, HttpServletResponse res) {
        return service.updateEmployee(employee, id);
    }

    @DeleteMapping("/employees/{id}")
    public void delete (@PathVariable Integer id) {
        service.deleteEmployee(id);
    }

}
