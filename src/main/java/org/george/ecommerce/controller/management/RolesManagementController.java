package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.RolesModel;
import org.george.ecommerce.service.RolesServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/roles")
public class RolesManagementController {
    final RolesServiceImpl rolesService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<RolesModel>> getAllRoles(Pageable pageable) {
        return ResponseEntity.ok().body(rolesService.getAllRoles(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RolesModel> createRole(
            @Valid @RequestBody RolesModel role) {
        return ResponseEntity.ok().body(rolesService.createRole(role));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteRole(
            @Valid @RequestBody RolesModel role) {
        rolesService.deleteRole(role);
        return ResponseEntity.ok().body("Deleted");
    }
}
