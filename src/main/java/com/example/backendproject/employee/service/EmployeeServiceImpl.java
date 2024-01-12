package com.example.backendproject.employee.service;


import com.example.backendproject.employee.entity.Employee;
import com.example.backendproject.employee.entity.Status;
import com.example.backendproject.employee.model.EmployeeDto;
import com.example.backendproject.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee:employeeList) {
            EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee employee1 = employeeRepository.findById(id).get();
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        employee1.setPassword(employee.getPassword());
        employee1.setStatus(employee.getStatus());
        employee1.setCreatedDate(employee.getCreatedDate());
        employee1.setCompany(employee.getCompany());
        return employeeRepository.save(employee1);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDto> getFindByStatus(Status status) {
        List<Employee> employeeList = employeeRepository.findByStatus(status);
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee:employeeList) {
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    @Override
    public List<EmployeeDto> getFindByName(String name) {
        List<Employee> employeeList = employeeRepository.findByNameContainsIgnoreCase(name);
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee:employeeList) {
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    @Override
    public List<EmployeeDto> getFindByEmail(String email) {
        List<Employee> employeeList = employeeRepository.findByEmailContainsIgnoreCase(email);
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee:employeeList) {
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    @Override
    public List<Employee> getByCompanyId(Long id) {
        return employeeRepository.findByCompanyId(id);
    }

    @Override
    public Boolean getExistingCompanyId(Long id) {
        return employeeRepository.existsAllByCompanyId(id);
    }
    public Page<Employee> getEmployeesByCompanyId(Long companyId, Pageable pageable) {
        return employeeRepository.findByCompanyId(companyId, pageable);
    }
}
