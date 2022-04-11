package org.george.ecommerce.controller.open;

import lombok.AllArgsConstructor;
import org.george.ecommerce.security.config.JwtConfig;
import org.george.ecommerce.service.UsersServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
@RequestMapping("/open")
@EnableConfigurationProperties(JwtConfig.class)
public class JwtController {

    private final UsersServiceImpl usersService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        //TODO
    }
}
