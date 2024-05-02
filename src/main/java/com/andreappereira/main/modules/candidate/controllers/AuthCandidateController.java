package com.andreappereira.main.modules.candidate.controllers;


import com.andreappereira.main.modules.candidate.dto.AuthCandidateRequestDTO;
import com.andreappereira.main.modules.candidate.dto.AuthCandidateResponseDTO;
import com.andreappereira.main.modules.candidate.useCases.AuthCandidateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate")
public class AuthCandidateController {
    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Profile authentication", description = "Candidate profile authentication.")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(schema = @Schema(implementation = AuthCandidateResponseDTO.class))
            }
        )
    })
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
