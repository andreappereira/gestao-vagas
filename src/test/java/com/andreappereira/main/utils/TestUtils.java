package com.andreappereira.main.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TestUtils {
    // @Value("${security.token.secret.company}")
    // private String secretKey;

    public static String objectToJson(Object obj){
        try{
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID idCompany, String secretKey) {
        
        var expiresIn = Instant.now().plus(Duration.ofHours(1));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("andreappereira")
        .withExpiresAt(expiresIn)
        .withSubject(idCompany.toString())
        .withClaim("roles", Arrays.asList("COMPANY"))
        .sign(algorithm);

        return token;
    }
    
}
