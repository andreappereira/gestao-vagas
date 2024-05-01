package com.andreappereira.main.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.andreappereira.main.modules.candidate.CandidateEntity;
import com.andreappereira.main.modules.candidate.useCases.CreateCandidateUseCase;
import com.andreappereira.main.modules.candidate.useCases.ProfileCandidateUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> postCreate(@Valid @RequestBody CandidateEntity candidate) {        
        try {
            var response = this.createCandidateUseCase.handleCreate(candidate);
            return ResponseEntity.ok().body(response); 
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var response = this.profileCandidateUseCase.handleGet(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(response);

        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
