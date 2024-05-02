package com.andreappereira.main.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponsetDTO {
    private UUID id;

    @Schema(example = "Andre Pereira")
    private String name;

    @Schema(example = "andre")
    private String username;

    @Schema(example = "andre.pereira@email.com")
    private String email;

    @Schema(example = "Software Developer")
    private String description;
}
