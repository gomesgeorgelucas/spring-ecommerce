package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.service.UsersServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/users")
public class UsersManagementController {
    final UsersServiceImpl usersService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UsersModel>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok().body(usersService.getAllUsers(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userLogin}")
    public ResponseEntity<UsersModel> getUserByUserLogin(
            @PathVariable("userLogin") String userLogin) {
        return ResponseEntity.ok().body(usersService.getUserByUserLogin(userLogin));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UsersModel> getUserByUserId(
            @PathVariable("userId") Long userId) {
        return ResponseEntity.ok().body(usersService.getUserByUserId(userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<UsersModel> createUser(
            @Valid @RequestBody UsersModel usersModel) {
        return ResponseEntity.ok().body(usersService.createUser(usersModel));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<UsersModel> updateUser(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody UsersModel usersModel) {
        return ResponseEntity.ok().body(usersService.updateUser(userId, usersModel));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<String> deleteUser(
            @Valid @RequestBody UsersModel usersModel) {
        usersService.deleteUser(usersModel);
        return ResponseEntity.ok().body("Deleted");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("userId") Long userId) {
        usersService.deleteUserByUserId(userId);
        return ResponseEntity.ok().body("Deleted");
    }
}
