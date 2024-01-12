package com.example.backendproject.employee.service;

import com.example.backendproject.employee.entity.Employee;
import com.example.backendproject.employee.entity.Status;
import com.example.backendproject.employee.model.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployee();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    List<EmployeeDto> getFindByStatus(Status status);
    List<EmployeeDto> getFindByName(String name);
    List<EmployeeDto> getFindByEmail(String email);
    List<Employee> getByCompanyId(Long id);
    Boolean getExistingCompanyId(Long id);
    Page<Employee> getEmployeesByCompanyId(Long companyId, Pageable pageable);

}
