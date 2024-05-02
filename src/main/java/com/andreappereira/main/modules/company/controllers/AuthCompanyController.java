package com.andreappereira.main.modules.company.controllers;

import com.andreappereira.main.modules.company.dto.AuthCompanyRequestDTO;
import com.andreappereira.main.modules.company.dto.AuthCompanyResponseDTO;
import com.andreappereira.main.modules.company.useCases.AuthCompanyUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/company")
@Tag(name = "Company")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Profile authentication", description = "Company profile authentication.")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {
                @Content(schema = @Schema(implementation = AuthCompanyResponseDTO.class))
            }
        )
    })
    public ResponseEntity<Object> postAuth(@RequestBody AuthCompanyRequestDTO authCompanyDTO) {
        try {
            var response = this.authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(response);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } 
    }
    
}
