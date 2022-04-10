package org.george.ecommerce.security.auth;

import java.util.Optional;

public interface ApplicationUserRepository {
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
