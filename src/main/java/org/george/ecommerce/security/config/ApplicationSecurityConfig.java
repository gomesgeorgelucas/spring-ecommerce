package org.george.ecommerce.security.config;

import lombok.AllArgsConstructor;
import org.george.ecommerce.security.auth.ApplicationUserService;
import org.george.ecommerce.security.jwt.JwtTokenVerifier;
import org.george.ecommerce.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;

import static org.george.ecommerce.domain.enums.UserRoleEnum.ROLE_ADMIN;
import static org.george.ecommerce.domain.enums.UserRoleEnum.ROLE_USER;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                    .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/","index","/css/*","/js/*","/open/**").permitAll()
                    .antMatchers("/api/**").hasAnyAuthority(ROLE_USER.name(), ROLE_ADMIN.name())
                    .antMatchers("/management/api/**").hasAnyAuthority(ROLE_ADMIN.name())
                    .anyRequest()
                    .authenticated();
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .defaultSuccessUrl("/main", true)
//                    .passwordParameter("password")
//                    .usernameParameter("username")
//                .and()
//                    .rememberMe()
//                    //session active for 7 days
//                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7L))
//                    //key for changing active session duration (cookie)
//                    .key("secureKeyFor7DaysOfAuthentication123")
//                .rememberMeParameter("remember-me")
//                .and()
//                    .logout()
//                    .logoutUrl("/logout")
//                    //protection against csrf attacks when disable - logout should be made by POST when enabling csrf
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                    .clearAuthentication(true)
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID", "remember-me")
//                    .logoutSuccessUrl("/login")
//                .and()
//                    .httpBasic();
    }

    /**
     * Enable OpenAPI related endpoints
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }


}
