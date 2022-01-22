package com.mdm.employee.service;

import com.mdm.employee.entities.Employee;
import com.mdm.employee.repositories.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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
        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }
}
