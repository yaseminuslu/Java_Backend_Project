package com.example.backendproject.company.service;

import com.example.backendproject.company.entity.Company;
import com.example.backendproject.company.model.CompanyDto;

import java.util.List;

public interface CompanyService {
    Company getByCompanyId(Long id);
    List<CompanyDto> getAllCompany();
    Company createCompany(Company company);
    Company updateCompany(Long id,Company company);
    void deleteCompany(Long id);
    List<CompanyDto> getFindByCompanyName(String name);
    List<CompanyDto> getFindByCompanyAddress(String email);
}
