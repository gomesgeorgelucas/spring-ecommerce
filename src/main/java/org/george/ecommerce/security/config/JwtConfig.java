package org.george.ecommerce.security.config;

import com.google.common.net.HttpHeaders;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "application.jwt")
public record JwtConfig(
        String secretKey,
        String tokenPrefix,
        Integer tokenExpirationAfterDays) {

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}



