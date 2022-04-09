package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.RolesModel;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.exception.InvalidRequestException;
import org.george.ecommerce.repository.UsersRepository;
import org.george.ecommerce.service.interfaces.IUsersService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;

import static org.george.ecommerce.domain.enums.UserRoleEnum.ADMIN;
import static org.george.ecommerce.domain.enums.UserRoleEnum.USER;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements IUsersService {
    final UsersRepository usersRepository;
    final RolesServiceImpl rolesService;
    final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    @Override
    public Page<UsersModel> getAllUsers(Pageable pageable) {
       return usersRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public UsersModel getUserByUserId(Long userId) {
        if (usersRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("Not found");
        }

        UsersModel stored = usersRepository.findById(userId).get();
        return stored;
    }

    @Transactional
    @Override
    public UsersModel getUserByUserLogin(String userLogin) {
        if (usersRepository.findByUserLogin(userLogin).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel stored = usersRepository.findByUserLogin(userLogin).get();
        return stored;
    }

    @Transactional
    @Override
    public UsersModel createUser(UsersModel usersModel) {
        RolesModel userRole = rolesService.getRoleByName(usersModel.getUserRole().getRoleName());
        usersModel.setUserRole(userRole);
        return usersRepository.save(usersModel);
    }

    @Transactional
    @Override
    public UsersModel createRegularUser(UsersModel usersModel) {
        if (usersModel.getUserRole() != null && usersModel.getUserRole().getRoleName() == ADMIN) {
            throw new InvalidRequestException();
        }
        RolesModel userRole = rolesService.getRoleByName(USER);
        usersModel.setUserRole(userRole);
        return usersRepository.save(usersModel);
    }

    @Transactional
    @Override
    public UsersModel updateRegularUser(String userLogin, UsersModel usersModel) {
        if (usersRepository.findByUserLogin(userLogin).isEmpty()) {
            throw new NotFoundException("User not found");
        }

        if (usersModel.getUserRole() != null && usersModel.getUserRole().getRoleName() == ADMIN) {
            throw new InvalidRequestException();
        }

        usersModel.setUserRole(rolesService.getRoleByName(USER));
        UsersModel stored = usersRepository.findByUserLogin(userLogin).get();

        if (stored.getUserRole().getRoleName() != usersModel.getUserRole().getRoleName()) {
            throw new InvalidRequestException();
        }
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(usersModel, stored);
        return usersRepository.save(stored);
    }

    @Transactional
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

    @Transactional
    @Override
    public void deleteUser(UsersModel usersModel)
    {
        if (usersRepository.findByUserLogin(usersModel.getUserLogin()).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel stored = usersRepository.findByUserLogin(usersModel.getUserLogin()).get();
        usersRepository.deleteById(stored.getUserId());
    }

    @Transactional
    @Override
    public void deleteUserByUserLogin(String userLogin) {
        if (usersRepository.findByUserLogin(userLogin).isEmpty()) {
            throw new NotFoundException("User not found");
        }

        if (usersRepository.findByUserLogin(userLogin).isPresent()) {
            if (usersRepository.findByUserLogin(userLogin).get()
                    .getUserRole().getRoleName() == ADMIN) {
                throw new InvalidRequestException();
            }
        }

        usersRepository.deleteById(usersRepository.findByUserLogin(userLogin).get()
                .getUserId());
    }

    @Transactional
    @Override
    public void deleteUserByUserId(Long userId) {
        if (usersRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel stored = usersRepository.findById(userId).get();
        usersRepository.deleteById(stored.getUserId());
    }
}
