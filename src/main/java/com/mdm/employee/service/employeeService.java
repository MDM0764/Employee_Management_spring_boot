package com.mdm.employee.service;

import com.mdm.employee.entities.Employee;
import com.mdm.employee.repositories.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class employeeService {

    @Autowired
    private employeeRepository repository;

    public Page<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())){
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }
        PageRequest paging = PageRequest.of(pageNo, pageSize, sort);

        Page<Employee> pagedResult = repository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }

    public void saveEmployee(Employee employee, HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            repository.save(employee);
            String path = req.getRequestURL().toString();
            res.sendRedirect(path);
        } catch (Exception e) {
            System.out.println("Error occurred while deleting ");
            System.out.println(e.getStackTrace());
            String path = req.getRequestURL().toString();
            res.sendRedirect(path + "/error");
        }
    }

    public String updateEmployee(Employee employee,Integer id) {
        try {
            Employee oldEmpData = repository.findById(id).get();
            oldEmpData.setEmail(employee.getEmail()); // put in service part
            oldEmpData.setAddress(employee.getAddress());
            oldEmpData.setName(employee.getName());
            oldEmpData.setPhoneNo(employee.getPhoneNo());
            repository.save(oldEmpData);
            return "redirect:/employee";
        }  catch (Exception e) {
            System.out.println("Error occurred while updating ");
            System.out.println(e.getStackTrace());
            return "redirect:/error";
        }

    }

    public void deleteEmployee(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error occurred while deleting ");
            System.out.println(e.getStackTrace());
        }

    }

    public void setRepository(employeeRepository repository) {
        this.repository = repository;
    }

}
