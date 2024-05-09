package com.andreappereira.main.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.andreappereira.main.modules.company.CompanyEntity;
import com.andreappereira.main.modules.company.useCases.CreateCompanyUseCase;
import com.andreappereira.main.modules.job.JobEntity;
import com.andreappereira.main.modules.job.dto.CreateJobDTO;
import com.andreappereira.main.modules.job.useCases.CreateJobUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/company")
@Tag(name = "Company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @Operation(summary = "Create profile", description = "Create a new profile company.")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(schema = @Schema(implementation = CompanyEntity.class))
            }
        )
    })
    public ResponseEntity<Object> postCreate(@Valid @RequestBody CompanyEntity company) {
        try{
            var response = this.createCompanyUseCase.handleCreate(company);
            return ResponseEntity.ok().body(response);

        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/job")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Create a job.", description = "Company create a new job.")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(schema = @Schema(implementation = JobEntity.class))
            }
        )
    })
    public ResponseEntity<Object> postCreateJob(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        try {
            var companyId = request.getAttribute("company_id");
            // job.setCompanyId(UUID.fromString(companyId.toString()));

            var job = JobEntity.builder()
            .companyId(UUID.fromString(companyId.toString()))
            .benefits(createJobDTO.getBenefits())
            .description(createJobDTO.getDescription())
            .build();

            var response = this.createJobUseCase.handleCreate(job);

            return ResponseEntity.ok().body(response);

        }catch(Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
     
}
