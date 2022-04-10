package org.george.ecommerce.security.auth;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ApplicationUserRepositoryService implements ApplicationUserRepository {

    final PasswordEncoder passwordEncoder;
    final UsersServiceImpl usersService;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username)
    {
        return Optional.of(getApplicationUserByUsername(username));
    }

    public ApplicationUser getApplicationUserByUsername(String username) {
        UsersModel persistedUser = usersService.getUserByUserLogin(username);

        return ApplicationUser.builder()
                .username(persistedUser.getUserName())
                .password(persistedUser.getUserPassword())
                .grantedAuthorities(Collections.singletonList(new SimpleGrantedAuthority(persistedUser.getUserRole().getRoleName().name())))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

    }


}
