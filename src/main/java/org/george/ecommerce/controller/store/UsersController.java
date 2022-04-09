package org.george.ecommerce.controller.store;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.service.UsersServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/api/store/users")
public class UsersController {
    final UsersServiceImpl usersService;

    @GetMapping("/login/{login}")
    public ResponseEntity<UsersModel> getUserByUserLogin(@Valid @PathVariable("login") String userLogin) {
        return ResponseEntity.ok().body(usersService.getUserByUserLogin(userLogin));
    }

    @PostMapping()
    public ResponseEntity<UsersModel> createUser( @Valid @RequestBody UsersModel usersModel) {
        return ResponseEntity.ok().body(usersService.createUser(usersModel));
    }

    @PutMapping("/id/{userId}")
    public ResponseEntity<UsersModel> updateUser(@PathVariable("userId") Long userId,@Valid @RequestBody UsersModel usersModel) {
        return ResponseEntity.ok().body(usersService.updateUser(userId, usersModel));
    }

    @DeleteMapping("/login/{userLogin}")
    public ResponseEntity<String> deleteUserByUserLogin(@PathVariable("userLogin") String userLogin) {
        usersService.deleteUserByUserLogin(userLogin);
        return ResponseEntity.ok().body("Deleted");
    }
}
