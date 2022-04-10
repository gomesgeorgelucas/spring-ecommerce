package org.george.ecommerce.controller.open;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.service.UsersServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/open/users")
public class OpenUsersController {
    final UsersServiceImpl usersService;

    @PostMapping
    public ResponseEntity<UsersModel> createRegularUser(
            @RequestBody
            @Valid UsersModel usersModel) {
        return ResponseEntity.ok().body(usersService.createRegularUser(usersModel));
    }
}
