package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.user.UsersModel;
import org.george.ecommerce.repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UsersService {
    final UsersRepository usersRepository;

    public Page<UsersModel> getUsers(Pageable page) {
        return usersRepository.findAll(page);
    }

    public UsersModel save(UsersModel user) {
        return usersRepository.save(user);
    }
}
