package com.example.backendproject.employee.model;

import com.example.backendproject.company.entity.Company;
import com.example.backendproject.employee.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private String name;
    private String email;
    private Status status;
    private Company company;
}
