package com.example.backendproject.employee.security;

import com.example.backendproject.company.entity.Company;
import com.example.backendproject.employee.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Status status;
    private LocalDateTime created_date=LocalDateTime.now();
    private Company company;

}
