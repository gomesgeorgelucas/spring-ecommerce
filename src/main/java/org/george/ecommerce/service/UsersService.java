package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.RolesModel;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.repository.UsersRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@AllArgsConstructor
@Service
public class UsersService implements IUsersService {
    final UsersRepository usersRepository;
    final RolesService rolesService;
    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<UsersModel> getAllUsers(Pageable pageable) {
       return usersRepository.findAll(pageable);
    }

    @Override
    public UsersModel getUserByUserId(Long userId) {
        if (usersRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("Not found");
        }

        UsersModel stored = usersRepository.findById(userId).get();
        return stored;
    }

    @Override
    public UsersModel getUserByUserLogin(String userLogin) {
        if (usersRepository.findByUserLogin(userLogin).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel stored = usersRepository.findByUserLogin(userLogin).get();
        return stored;
    }

    @Override
    public UsersModel createUser(UsersModel usersModel) {
        RolesModel userRole = rolesService.getRoleByName(usersModel.getUserRole().getRoleName());
        usersModel.setUserRole(userRole);
        return usersRepository.save(usersModel);
    }

    @Override
    public UsersModel updateUser(Long userId, UsersModel usersModel) {
        if (usersRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("User not found");
        }

        UsersModel stored = usersRepository.findById(userId).get();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(usersModel, stored);
        return usersRepository.save(stored);
    }

    @Override
    public void deleteUser(UsersModel usersModel)
    {
        if (usersRepository.findByUserLogin(usersModel.getUserLogin()).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel stored = usersRepository.findByUserLogin(usersModel.getUserLogin()).get();
        usersRepository.deleteById(stored.getUserId());
    }

    @Override
    public void deleteUserByUserId(Long userId) {
        if (usersRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel stored = usersRepository.findById(userId).get();
        usersRepository.deleteById(stored.getUserId());
    }
}
