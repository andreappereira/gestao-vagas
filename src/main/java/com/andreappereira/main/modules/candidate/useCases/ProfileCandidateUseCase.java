package com.andreappereira.main.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.andreappereira.main.modules.candidate.dto.ProfileCandidateResponsetDTO;
import com.andreappereira.main.modules.candidate.repositories.CandidateRepository;


@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponsetDTO handleGet(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        var candidtaeDTO = ProfileCandidateResponsetDTO.builder()
        .description(candidate.getDescription())
        .email(candidate.getEmail())
        .id(candidate.getId())
        .name(candidate.getName())
        .username(candidate.getUsername())
        .build();

        return candidtaeDTO;
    }
    
}
