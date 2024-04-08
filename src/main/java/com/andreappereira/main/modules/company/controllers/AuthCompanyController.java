package com.andreappereira.main.modules.company.controllers;

import com.andreappereira.main.modules.company.dto.AuthCompanyRequestDTO;
import com.andreappereira.main.modules.company.useCases.AuthCompanyUseCase;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/company")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> postAuth(@RequestBody AuthCompanyRequestDTO authCompanyDTO) {
        try {
            var response = this.authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(response);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } 
    }
    
}
