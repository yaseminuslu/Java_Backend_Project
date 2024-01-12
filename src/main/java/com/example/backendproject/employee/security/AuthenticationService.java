package com.example.backendproject.employee.security;

import com.example.backendproject.company.repository.CompanyRepository;
import com.example.backendproject.employee.config.JwtService;
import com.example.backendproject.employee.entity.Employee;
import com.example.backendproject.employee.exception.CompanyNotFoundException;
import com.example.backendproject.employee.model.EmailUtil;
import com.example.backendproject.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (!companyRepository.existsById(request.getCompany().getId())) {
            log.error("Company does not exist.");
            throw new CompanyNotFoundException("The company with id " + request.getCompany() + " was not found.");
        }
        if (!employeeRepository.existsByEmail(request.getEmail())) {
            var employee = Employee.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .status(request.getStatus())
                    .createdDate(request.getCreated_date())
                    .company(request.getCompany())
                    .build();

            employee.setActivationCode(generateActivationCode());
            employee.setActivationCodeExpiration(LocalDateTime.now().plusMinutes(1));

                employeeRepository.save(employee);
            sendActivationCodeByEmail(employee.getEmail(), employee.getActivationCode());

                var jwtToken = jwtService.generateToken(employee);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
            }
         else {
            throw new RuntimeException("Employee already exists.");
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var employee = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(employee);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public void activateAccount(String email, String activationCode) {
        Employee employee = employeeRepository.findByEmailAndActivationCode(email, activationCode);

        if (employee != null) {
            if (employee.isActivationCodeExpired()) {
                throw new RuntimeException("Activation has expired. Please request a new activation code.");
            } else {
                employee.setActivated(true);
                employeeRepository.save(employee);
            }
        } else {
            throw new RuntimeException("Invalid user or activation code.");
        }
    }

    private String generateActivationCode() {
        Random random = new Random();
        StringBuilder activationCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            activationCode.append(random.nextInt(10));
        }
        return activationCode.toString();
    }
    private void sendActivationCodeByEmail(String email, String activationCode) {
        EmailUtil.sendActivationCodeByEmail(email, activationCode);
    }
}

