package com.andreappereira.main.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreappereira.main.exceptions.UserFoundException;
import com.andreappereira.main.modules.candidate.CandidateEntity;
import com.andreappereira.main.modules.candidate.controllers.CandidateRepository;


@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity handleCreate(CandidateEntity candidate) {
        this.candidateRepository
            .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });
        
        return this.candidateRepository.save(candidate);
    }
    
}
