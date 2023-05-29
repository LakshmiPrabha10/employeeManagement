package com.example.employeeManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class EmployeeDTO {
    @Id
    private Integer id;
    @NotNull(message = "name should not be null")
    @Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should have alphabets only")
    private String name;

    @NotNull(message = "Aadhar should not be null")
    @Pattern(regexp = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$", message = "Aadhar number is not valid")
    private String aadhar;
    private int age;
    private String department;
    private String city;
    @PastOrPresent(message = "Date should not be of future")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAadhar() {
        return aadhar;
    }

}

