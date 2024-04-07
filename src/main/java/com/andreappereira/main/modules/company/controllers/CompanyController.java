package com.andreappereira.main.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.andreappereira.main.modules.company.CompanyEntity;
import com.andreappereira.main.modules.company.useCases.CreateCompanyUseCase;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> postCreate(@Valid @RequestBody CompanyEntity company) {
        try{
            var response = this.createCompanyUseCase.handleCreate(company);
            return ResponseEntity.ok().body(response);

        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
