package com.andreappereira.main.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

import com.andreappereira.main.modules.candidate.dto.ProfileCandidateResponsetDTO;
import com.andreappereira.main.modules.candidate.entities.CandidateEntity;
import com.andreappereira.main.modules.candidate.useCases.CreateCandidateUseCase;
import com.andreappereira.main.modules.candidate.useCases.ProfileCandidateUseCase;
import com.andreappereira.main.modules.job.JobEntity;
import com.andreappereira.main.modules.job.useCases.ListAllJobsByFilterUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;


    @PostMapping("/")
    @Operation(summary = "Create profile", description = "Create a new profile candidate.")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(schema = @Schema(implementation = CandidateEntity.class))
            }
        )
    })
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
    @Operation(summary = "Candidate profile", description = "Cadidate information.")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(schema = @Schema(implementation = ProfileCandidateResponsetDTO.class))
            }
        ),
        @ApiResponse(responseCode = "400", description = "User not found")
    })
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var response = this.profileCandidateUseCase.handleGet(UUID.fromString(idCandidate.toString()));

            return ResponseEntity.ok().body(response);

        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Search a job.", description = "Candidate search a job.")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<List<JobEntity>> getJobsByFilter(@RequestParam String search) {
        try {
            var response = this.listAllJobsByFilterUseCase.handleFilter(search);

            return ResponseEntity.ok().body(response);

        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
