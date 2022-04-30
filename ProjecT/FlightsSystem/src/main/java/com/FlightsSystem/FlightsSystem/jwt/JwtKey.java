package com.FlightsSystem.FlightsSystem.jwt;


import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtKey {



    private final JwtConfig jwtConfig;
    @Autowired
    public JwtKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public SecretKey SecretKey(){
        return Keys.hmacShaKeyFor(jwtConfig.getKey().getBytes());
    }
}
