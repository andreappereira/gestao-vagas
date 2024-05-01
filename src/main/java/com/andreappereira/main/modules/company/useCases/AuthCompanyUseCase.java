package com.andreappereira.main.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andreappereira.main.modules.company.dto.AuthCompanyRequestDTO;
import com.andreappereira.main.modules.company.dto.AuthCompanyResponseDTO;
import com.andreappereira.main.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret.company}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthCompanyResponseDTO execute(AuthCompanyRequestDTO authCompanyRequestDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyRequestDTO.username())
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("Username or password incorrect.");
            });

        var passwordMatches = this.passwordEncoder.matches(authCompanyRequestDTO.password(), company.getPassword());

        var expiresIn = Instant.now().plus(Duration.ofHours(1));

        if(!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("andreappereira")
        .withExpiresAt(expiresIn)
        .withSubject(company.getId().toString())
        .withClaim("roles", Arrays.asList("COMPANY"))
        .sign(algorithm);

        var response = AuthCompanyResponseDTO.builder()
        .acess_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

        return response;

    }
    
}
