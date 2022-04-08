package org.george.ecommerce.service;

import org.george.ecommerce.domain.dto.user.UserDTO;
import org.george.ecommerce.domain.model.UsersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsersService {
    Page<UserDTO> getAllUsers(Pageable pageable);
    UserDTO getUserById(Long id);
    UsersModel createUser(UserDTO userDTO);
    UsersModel updateUser(Long id, UserDTO userDTO);
    void deleteUser(UserDTO userDTO);
}
