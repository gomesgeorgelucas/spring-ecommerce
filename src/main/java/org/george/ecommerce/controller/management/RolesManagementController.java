package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.RolesModel;
import org.george.ecommerce.service.RolesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<RolesModel> createRole(@Valid @RequestBody RolesModel role) {
        return ResponseEntity.ok().body(rolesService.createRole(role));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRole(@Valid @RequestBody RolesModel role) {
        rolesService.deleteRole(role);
        return ResponseEntity.ok().body("Deleted");
    }
}
