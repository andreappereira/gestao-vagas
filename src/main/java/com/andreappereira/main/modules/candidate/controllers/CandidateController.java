package com.andreappereira.main.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.andreappereira.main.exceptions.UserFoundException;
import com.andreappereira.main.modules.candidate.CandidateEntity;
import com.andreappereira.main.modules.candidate.useCases.CreateCandidateUseCase;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> postCreate(@Valid @RequestBody CandidateEntity candidate) {        
        try {
            var response = this.createCandidateUseCase.handleCreate(candidate);
            return ResponseEntity.ok().body(response); 
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
