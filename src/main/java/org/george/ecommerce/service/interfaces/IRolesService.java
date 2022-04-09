package org.george.ecommerce.service.interfaces;

import org.george.ecommerce.domain.enums.UserRoleEnum;
import org.george.ecommerce.domain.model.RolesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRolesService {
    Page<RolesModel> getAllRoles(Pageable pageable);
    RolesModel createRole(RolesModel role);
    RolesModel getRoleByName(UserRoleEnum roleEnum);
    void deleteRole(RolesModel role);
}
