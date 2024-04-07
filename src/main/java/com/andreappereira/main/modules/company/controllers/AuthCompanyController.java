package com.andreappereira.main.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.andreappereira.main.modules.company.dto.AuthCompanyDTO;
import com.andreappereira.main.modules.company.useCases.AuthCompanyUseCase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> postAuth(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var response = this.authCompanyUseCase.execute(authCompanyDTO);

            return ResponseEntity.ok().body(response);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } 
    }
    
}
