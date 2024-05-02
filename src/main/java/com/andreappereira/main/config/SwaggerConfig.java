package com.andreappereira.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
        .info(new Info().title("Vacancies management").description("API designed to for job vacancies management.").version("V1"))
        .schemaRequirement("jwt_auth", crateSecurityScheme());
        // .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        // .components(new Components().addSecuritySchemes("Bearer Authentication", crateSecurityScheme()));
    }

    private SecurityScheme crateSecurityScheme() {
        return new SecurityScheme()
        .name("jwt_auth")
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT");
    }

    
}
