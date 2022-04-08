package org.george.ecommerce.controller.management;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.dto.user.UserDTO;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/management/api/store/users")
public class UsersManagementController {
    final UsersService usersService;

    @GetMapping()
    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pageable) {
        Page<UserDTO> page = usersService.getAllUsers(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUsers(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok().body(usersService.getUserById(userId));
    }

    @PostMapping()
    public ResponseEntity<UsersModel> save(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok().body(usersService.createUser(userDTO));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody UserDTO userDTO) {
        usersService.deleteUser(userDTO);
        return ResponseEntity.ok().body("Deleted");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> update(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        usersService.updateUser(userId, userDTO);
        return ResponseEntity.ok().body("Updated!");
    }
}
