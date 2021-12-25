package com.mdm.employee.controller;


import com.mdm.employee.entities.Employee;
import com.mdm.employee.repositories.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String save(Employee employe){
         repository.save(employe);
        return "redirect:/employee";
    }

    @PutMapping("/employee/{id}")
    public Employee update(@RequestBody Employee employee, @PathVariable Integer id) {
        Employee oldEmpData = repository.findById(id).get();
        oldEmpData.setEmail(employee.getEmail());
        oldEmpData.setAddress(employee.getAddress());
        oldEmpData.setName(employee.getName());
        oldEmpData.setPhoneNo(employee.getPhoneNo());
        return repository.save(oldEmpData);
    }

    @DeleteMapping("/employees/{id}")
    public void delete (@PathVariable Integer id) {
        repository.deleteById(id);
    }

}
