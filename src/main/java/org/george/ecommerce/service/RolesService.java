package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.enums.UserRoleEnum;
import org.george.ecommerce.domain.model.RolesModel;
import org.george.ecommerce.repository.RolesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@AllArgsConstructor
@Service
public class RolesService implements IRolesService{
    final RolesRepository rolesRepository;

    @Override
    public Page<RolesModel> getAllRoles(Pageable pageable) {
        return rolesRepository.findAll(pageable);
    }

    @Override
    public RolesModel createRole(RolesModel role) {
        return rolesRepository.save(role);
    }

    @Override
    public void deleteRole(RolesModel role) {
        if (rolesRepository.findByRoleName(role.getRoleName()).isEmpty()) {
            throw new NotFoundException("Role not found");
        }

        RolesModel rolesModel = rolesRepository.findByRoleName(role.getRoleName()).get();

        rolesRepository.delete(rolesModel);
    }

    @Override
    public RolesModel getRoleByName(UserRoleEnum roleEnum) {
        if (rolesRepository.findByRoleName(roleEnum).isEmpty()) {
            throw new NotFoundException("Role not found");
        }
        return rolesRepository.findByRoleName(roleEnum).get();
    }
}
