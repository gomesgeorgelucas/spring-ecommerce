package org.george.ecommerce.controller.store;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.service.UsersServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/api/store/users")
public class UsersController {
    private final UsersServiceImpl usersService;

    @PreAuthorize("#userLogin == authentication.name or hasRole('ADMIN')")
    @GetMapping("/{userLogin}")
    public ResponseEntity<UsersModel> getUserByUserLogin(
            @PathVariable("userLogin")
            @Param("userLogin") String userLogin) {
        return ResponseEntity.ok().body(usersService.getUserByUserLogin(userLogin));
    }

    @PreAuthorize("#userLogin == authentication.name or hasRole('ADMIN')")
    @PutMapping("/{userLogin}")
    public ResponseEntity<UsersModel> updateRegularUser(
            @PathVariable("userLogin")
            @Param("userLogin") String userLogin,
            @Valid @RequestBody UsersModel usersModel) {
        return ResponseEntity.ok().body(usersService.updateRegularUser(userLogin, usersModel));
    }

    @PreAuthorize("#userLogin == authentication.name or hasRole('ADMIN')")
    @DeleteMapping("/{userLogin}")
    public ResponseEntity<String> deleteUserByUserLogin(
            @PathVariable("userLogin")
            @Param("userLogin") String userLogin) {
        usersService.deleteUserByUserLogin(userLogin);
        return ResponseEntity.ok().body("Deleted");
    }
}
