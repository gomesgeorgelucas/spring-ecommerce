package org.george.ecommerce.service.interfaces;

import org.george.ecommerce.domain.model.UsersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsersService {
    Page<UsersModel> getAllUsers(Pageable pageable);
    UsersModel getUserByUserId(Long userId);
    UsersModel getUserByUserLogin(String userLogin);
    UsersModel createUser(UsersModel usersModel);
    UsersModel updateUser(Long id, UsersModel usersModel);
    void deleteUser(UsersModel usersModel);
    void deleteUserByUserId(Long userId);
    void deleteUserByUserLogin(String userLogin);
}
