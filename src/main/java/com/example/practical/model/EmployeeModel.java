package com.example.practical.model;

import com.example.practical.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeModel extends PagingAndSortingRepository<Employee, Integer> {
    @Query(value = "SELECT product FROM Employee product WHERE product.name LIKE :name%")
    Page<Employee> findAllEmployeeByName(String name, Pageable pageable);
}
