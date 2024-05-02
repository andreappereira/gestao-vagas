package com.andreappereira.main.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Andre Aparecido", requiredMode = RequiredMode.REQUIRED)
    private String name;

    @Email(message = "O campo [email] deve conter um e-mail válido.")
    @Schema(example = "andre.aparecido@email.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço.")
    @Schema(example = "andre", requiredMode = RequiredMode.REQUIRED)
    private String username;

    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres.")
    @Schema(example = "123456*Xpto", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED)
    private String password;

    @Schema(example = "Sofware Enginner", requiredMode = RequiredMode.REQUIRED)
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
