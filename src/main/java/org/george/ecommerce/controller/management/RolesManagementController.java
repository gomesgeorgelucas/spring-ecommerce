package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.RolesModel;
import org.george.ecommerce.service.RolesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/roles")
public class RolesManagementController {
    final RolesService rolesService;

    @GetMapping
    public ResponseEntity<Page<RolesModel>> getAllRoles(Pageable pageable) {
        return ResponseEntity.ok().body(rolesService.getAllRoles(pageable));
    }

    @PostMapping
    public ResponseEntity<RolesModel> createRole(RolesModel role) {
        return ResponseEntity.ok().body(rolesService.createRole(role));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRole(RolesModel rolesModel) {
        rolesService.deleteRole(rolesModel);
        return ResponseEntity.ok().body("Deleted");
    }
}
