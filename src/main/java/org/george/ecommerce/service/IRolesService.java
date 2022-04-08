package org.george.ecommerce.service;

import org.george.ecommerce.domain.model.RolesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRolesService {
    Page<RolesModel> getAllRoles(Pageable pageable);
    RolesModel createRole(RolesModel role);
    void deleteRole(RolesModel role);
}
