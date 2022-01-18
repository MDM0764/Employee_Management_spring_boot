package com.mdm.employee.repositories;

import com.mdm.employee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends PagingAndSortingRepository<Employee, Integer> {
}
