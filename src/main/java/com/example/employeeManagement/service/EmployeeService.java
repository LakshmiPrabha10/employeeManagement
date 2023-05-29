package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.EmployeeDTO;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() throws Exception {
        Iterable<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTO = new ArrayList<>();
        employees.forEach(employee -> {
            EmployeeDTO EmployeeDTO = new EmployeeDTO();
            EmployeeDTO.setId(employee.getId());
            EmployeeDTO.setDob(employee.getDob());
            EmployeeDTO.setAadhar(employee.getAadhar());
            EmployeeDTO.setName(employee.getName());
            EmployeeDTO.setAge(employee.getAge());
            EmployeeDTO.setDepartment(employee.getDepartment());
            EmployeeDTO.setCity(employee.getCity());
            employeeDTO.add(EmployeeDTO);
        });
        if (employeeDTO.isEmpty()) throw new Exception("Employees not found");
        return employeeDTO;
    }

    public EmployeeDTO getEmployee(Integer employeeId) throws Exception {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow(() -> new Exception("Employee does not exist"));
        EmployeeDTO EmployeeDTO = new EmployeeDTO();
        EmployeeDTO.setId(employee.getId());
        EmployeeDTO.setDob(employee.getDob());
        EmployeeDTO.setName(employee.getName());
        EmployeeDTO.setCity(employee.getCity());
        EmployeeDTO.setAge(employee.getAge());
        EmployeeDTO.setAadhar(employee.getAadhar());
        EmployeeDTO.setDepartment(EmployeeDTO.getDepartment());
        return EmployeeDTO;
    }

    public EmployeeDTO getEmployee(String aadhar) throws Exception {
        Employee employee = employeeRepository.findByAadhar(aadhar);
        if (employee == null || aadhar == "") {
            throw new Exception("Employee does not exist");
        }
        EmployeeDTO EmployeeDTO = new EmployeeDTO();
        EmployeeDTO.setId(employee.getId());
        EmployeeDTO.setDob(employee.getDob());
        EmployeeDTO.setName(employee.getName());
        EmployeeDTO.setCity(employee.getCity());
        EmployeeDTO.setAge(employee.getAge());
        EmployeeDTO.setAadhar(employee.getAadhar());
        EmployeeDTO.setDepartment(employee.getDepartment());
        return EmployeeDTO;
    }


    public void updateEmployee(String aadhar, String department) throws Exception {
        Employee employee = employeeRepository.findByAadhar(aadhar);
        if (employee == null || aadhar == "") {
            throw new Exception("Employee does not exist");
        }
        employee.setDepartment(department);

    }


    public Integer addEmployee(EmployeeDTO employeeDTO) throws Exception {
        Employee employee = new Employee();
        employee.setDob(employeeDTO.getDob());
        employee.setName(employeeDTO.getName());
        employee.setId(employeeDTO.getId());
        Employee employeeDuplicate = employeeRepository.findByAadhar(employeeDTO.getAadhar());
        if (employeeDuplicate!= null) {
            throw new Exception("Employee with Aadhar number " + employee.getAadhar() + " already exists");
        } else {
            employee.setAadhar(employeeDTO.getAadhar());
        }
        employee.setAge(employeeDTO.getAge());
        employee.setCity(employeeDTO.getCity());
        employee.setDepartment(employeeDTO.getDepartment());
        employeeRepository.save(employee);
        return employee.getId();
    }
}
