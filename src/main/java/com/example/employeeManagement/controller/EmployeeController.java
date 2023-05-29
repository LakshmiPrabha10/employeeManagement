package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.EmployeeDTO;
import com.example.employeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class EmployeeController {
        @Autowired
        private EmployeeService employeeService;

        @PostMapping(value = "/employee")
        public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws Exception {
            Integer Id = employeeService.addEmployee(employeeDTO);
            String successMessage = "INSERT_SUCCESS " + Id;
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        }

        @GetMapping(value = "/employee")
        public ResponseEntity<List<EmployeeDTO>> getAllEmployees() throws Exception {
            List<EmployeeDTO> customerList = employeeService.getAllEmployees();
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        }

        @GetMapping(value = "/employees/id/{employeeId}")
        public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable Integer EmployeeId) throws Exception {
            EmployeeDTO customer = employeeService.getEmployee(EmployeeId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }

        @GetMapping(value = "/employees/{employeeAadhar}")
        public ResponseEntity<EmployeeDTO> getEmployeeDetailsByAadhar(@PathVariable String employeeAadhar) throws Exception {
            EmployeeDTO customer = employeeService.getEmployee(employeeAadhar);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }


        @PutMapping(value = "/employees/{employeeAadhar}")
        public ResponseEntity<Object> updateEmployee(@PathVariable String employeeAadhar, @Valid @RequestBody EmployeeDTO employee)
                throws Exception {
            employeeService.updateEmployee(employeeAadhar, employee.getDepartment());
            String successMessage = "UPDATE_SUCCESS";
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        }

    }

