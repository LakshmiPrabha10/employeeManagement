package com.example.employeeManagement.repository;

import com.example.employeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByAadhar(String aadhar);
}
