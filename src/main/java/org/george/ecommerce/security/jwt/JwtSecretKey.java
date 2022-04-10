package org.george.ecommerce.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.george.ecommerce.security.config.JwtConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(JwtConfig.class)
public class JwtSecretKey {

    private final JwtConfig jwtConfig;

    @Bean
    public SecretKey getSecretKeyForSigning() {
        return Keys.hmacShaKeyFor(jwtConfig.secretKey().getBytes());
    }
}
