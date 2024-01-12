package com.example.backendproject.company.service;

import com.example.backendproject.company.entity.Company;
import com.example.backendproject.company.model.CompanyDto;
import com.example.backendproject.company.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Company getByCompanyId(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public List<CompanyDto> getAllCompany() {
        List<Company> companyList = companyRepository.findAll();
        List<CompanyDto> companyDtoList=new ArrayList<>();
        for (Company company:companyList) {
            CompanyDto companyDto = modelMapper.map(company,CompanyDto.class);
            companyDtoList.add(companyDto);
        }
        return companyDtoList;
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Company company1= companyRepository.findById(id).get();
        company1.setName(company.getName());
        company1.setAddress(company.getAddress());
        return companyRepository.save(company1);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<CompanyDto> getFindByCompanyName(String name) {
        List<Company> companyList = companyRepository.findByNameContainsIgnoreCase(name);
        List<CompanyDto> companyDtoList=new ArrayList<>();
        for (Company company:companyList) {
            CompanyDto companyDto = modelMapper.map(company,CompanyDto.class);
            companyDtoList.add(companyDto);
        }
        return companyDtoList;
    }

    @Override
    public List<CompanyDto> getFindByCompanyAddress(String address) {
        List<Company> companyList = companyRepository.findByAddressContainsIgnoreCase(address);
        List<CompanyDto> companyDtoList=new ArrayList<>();
        for (Company company:companyList) {
            CompanyDto companyDto = modelMapper.map(company,CompanyDto.class);
            companyDtoList.add(companyDto);
        }
        return companyDtoList;
    }
}
