package com.andreappereira.main.modules.candidate.controllers;


import com.andreappereira.main.modules.candidate.dto.AuthCandidateRequestDTO;
import com.andreappereira.main.modules.candidate.useCases.AuthCandidateUseCase;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {
    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> postAuth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try{
            var response = this.authCandidateUseCase
            .execute(authCandidateRequestDTO);
            
            return ResponseEntity.ok().body(response);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
