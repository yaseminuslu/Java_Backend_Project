package com.example.backendproject.employee.repository;

import com.example.backendproject.employee.entity.Employee;
import com.example.backendproject.employee.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    List<Employee> findByNameContainsIgnoreCase(String name);
    List<Employee> findByEmailContainsIgnoreCase(String email);
    List<Employee> findByCompanyId(Long id);
    Boolean existsAllByCompanyId(Long id);
    List<Employee> findByStatus(Status status);
    Page<Employee> findByCompanyId(Long companyId, Pageable pageable);
    boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);
    Employee findByEmailAndActivationCode(String email, String activationCode);
}
