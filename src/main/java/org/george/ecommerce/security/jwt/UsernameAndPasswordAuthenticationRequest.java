package org.george.ecommerce.security.jwt;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
