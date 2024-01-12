package com.example.backendproject.employee.controller;

import com.example.backendproject.employee.entity.Employee;
import com.example.backendproject.employee.entity.Status;
import com.example.backendproject.employee.exception.CompanyNotFoundException;
import com.example.backendproject.employee.model.EmployeeDto;
import com.example.backendproject.employee.repository.EmployeeRepository;
import com.example.backendproject.employee.security.AuthenticationRequest;
import com.example.backendproject.employee.security.AuthenticationResponse;
import com.example.backendproject.employee.security.AuthenticationService;
import com.example.backendproject.employee.security.RegisterRequest;
import com.example.backendproject.employee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final AuthenticationService authenticationService;
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(AuthenticationService authenticationService, EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.authenticationService = authenticationService;
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PostMapping("auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        try {
            AuthenticationResponse response = authenticationService.register(request);
            System.out.println("The verification code has been sent to your e-mail address.");
            return ResponseEntity.ok(response);
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Company not found.");
        }
    }

    @PostMapping("auth/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestParam String email, @RequestParam String activationCode) {
        authenticationService.activateAccount(email, activationCode);
        return ResponseEntity.ok("Account activated successfully");
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/status")
    public List<EmployeeDto> getFindByStatus(
            @RequestParam(value = "status") Status status){
        return employeeService.getFindByStatus(status);
    }

    @GetMapping("/name")
    public List<EmployeeDto> getFindByName(
            @RequestParam(value = "name") String name){
        return employeeService.getFindByName(name);
    }

    @GetMapping("/email")
    public List<EmployeeDto> getFindByEmail(
            @RequestParam(value = "email") String email){
        return employeeService.getFindByEmail(email);
    }

    @GetMapping("/company/{id}")
    public List<Employee> getByCompanyId(@PathVariable("id") Long id){
        return employeeService.getByCompanyId(id);
    }

    @GetMapping("/companyExisting/{id}")
    public Boolean getExistingCompanyId(@PathVariable("id") Long id){
        return employeeService.getExistingCompanyId(id);
    }
    @GetMapping("/employeesByCompany/{companyId}")
    public ResponseEntity<Page<Employee>> getEmployeesByCompanyId(
            @PathVariable Long companyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeService.getEmployeesByCompanyId(companyId, pageable);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
