package com.andreappereira.main.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andreappereira.main.exceptions.UserFoundException;
import com.andreappereira.main.modules.company.CompanyEntity;
import com.andreappereira.main.modules.company.repositories.CompanyRepository;


@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity handleCreate(CompanyEntity company) {
        this.companyRepository
            .findByUsernameOrDocument(company.getUsername(), company.getDocument())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });
        
        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);

        return this.companyRepository.save(company);
    }
    
}
