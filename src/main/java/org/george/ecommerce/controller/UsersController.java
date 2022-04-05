package org.george.ecommerce.controller;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.user.UsersModel;
import org.george.ecommerce.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store")
public class UsersController {
    final UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<Page<UsersModel>> getUsers(Pageable page) {
        return ResponseEntity.ok().body(usersService.getUsers(page));
    }

    @PostMapping("/register")
    public ResponseEntity<UsersModel> register(UsersModel user) {
        return ResponseEntity.ok().body(usersService.save(user));
    }
}
