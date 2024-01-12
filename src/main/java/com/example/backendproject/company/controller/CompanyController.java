package com.example.backendproject.company.controller;

import com.example.backendproject.company.entity.Company;
import com.example.backendproject.company.model.CompanyDto;
import com.example.backendproject.company.repository.CompanyRepository;
import com.example.backendproject.company.service.CompanyService;
import com.example.backendproject.employee.controller.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")

public class CompanyController {
    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/{id}")
    public Company getByCompanyId(@PathVariable Long id){
        return companyService.getByCompanyId(id);
    }

    @GetMapping
    public List<CompanyDto> getAllCompany(){
        return companyService.getAllCompany();
    }
    @PreAuthorize("permitAll()")
    @PostMapping
    public Company createCompany(@RequestBody Company company){
        if(!companyRepository.existsByName(company.getName())){
            return companyService.createCompany(company);
        }
        else {
            log.error("Company already exists");
            return null;
        }
    }
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company company){
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

    @GetMapping("/name")
    public List<CompanyDto> getFindByCompanyName(@RequestParam(value = "name") String name){
        return companyService.getFindByCompanyName(name);
    }

    @GetMapping("/address")
    public List<CompanyDto> getFindByCompanyAddress(@RequestParam(value = "address") String address){
        return companyService.getFindByCompanyAddress(address);
    }
}

