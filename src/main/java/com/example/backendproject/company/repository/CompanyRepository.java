package com.example.backendproject.company.repository;

import com.example.backendproject.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    List<Company> findByNameContainsIgnoreCase(String name);
    List<Company> findByAddressContainsIgnoreCase(String address);
    boolean existsByName(String name);
    boolean existsById(Long id);
}

