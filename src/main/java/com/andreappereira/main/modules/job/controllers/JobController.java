package com.andreappereira.main.modules.job.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.andreappereira.main.modules.job.JobEntity;
import com.andreappereira.main.modules.job.dto.CreateJobDTO;
import com.andreappereira.main.modules.job.useCases.CreateJobUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;
    
    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> postCreate(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
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
