package com.mdm.employee.service;

import com.mdm.employee.entities.Employee;
import com.mdm.employee.repositories.employeeRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class employeeServiceTest {

    @Autowired
    private employeeRepository repo = mock(employeeRepository.class) ;

    @InjectMocks
    private employeeService serv = mock(employeeService.class);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    @DisplayName("get all employees test")
    void getAllEmployees() {
        assertNull(serv.getAllEmployees(5,1,"Name","ASC"));
    }

    @Test
    @DisplayName("save employees test")
    void saveEmployee() throws IOException {
        serv.setRepository(repo);
        Employee emp = new Employee();
        emp.setEmpId(10);
        emp.setName("Name");
        emp.setEmail("t@123gmail.com");
        emp.setPhoneNo("7894561236");
        emp.setAddress("address");
        serv.saveEmployee(emp, request, response);
    }

    @Test
    @DisplayName("update employees test")
    void updateEmployee() {
        Employee emp = new Employee();
        emp.setAddress("new addr");
        emp.setName("New");
        emp.setEmail("new@123gmail.com");
        emp.setPhoneNo("777794561236");
        assertNull(serv.updateEmployee(emp,20));
    }

    @Test
    @DisplayName("delete employees test")
    void deleteEmployee() {
    }
}