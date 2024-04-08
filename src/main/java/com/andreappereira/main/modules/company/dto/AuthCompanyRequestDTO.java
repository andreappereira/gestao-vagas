package com.andreappereira.main.modules.company.dto;

// import lombok.AllArgsConstructor;
// import lombok.Data;

// @Data
// @AllArgsConstructor
// public class AuthCompanyRequestDTO {
//     private String username;
//     private String password;
// }

public record AuthCompanyRequestDTO(String username, String password) {}
