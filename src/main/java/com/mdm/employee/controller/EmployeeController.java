package com.mdm.employee.controller;


import com.mdm.employee.entities.Employee;
import com.mdm.employee.repositories.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private employeeRepository repository;


    @GetMapping("/employees")
    public List<Employee> showAll() {
        return repository.findAll();
    }

    @PostMapping(value="/employee", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(Employee employee){
         repository.save(employee);
        return "redirect:/employee";
    }

    @PutMapping(value = "/employee/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(Employee employee, @PathVariable Integer id, HttpServletResponse res) {
        Employee oldEmpData = repository.findById(id).get();
        oldEmpData.setEmail(employee.getEmail());
        oldEmpData.setAddress(employee.getAddress());
        oldEmpData.setName(employee.getName());
        oldEmpData.setPhoneNo(employee.getPhoneNo());
        repository.save(oldEmpData);
        return "redirect:/employee";
    }

    @DeleteMapping("/employees/{id}")
    public void delete (@PathVariable Integer id) {
        repository.deleteById(id);
    }

}
