package org.george.ecommerce.repository;

import org.george.ecommerce.domain.enums.UserRoleEnum;
import org.george.ecommerce.domain.model.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {
    Optional<RolesModel> findByRoleName(UserRoleEnum roleName);
}
