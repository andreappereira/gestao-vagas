package com.andreappereira.main.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthCandidateResponseDTO {
    private String acess_token;
    private Long expires_in;
}
