package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.dto.user.UserDTO;
import org.george.ecommerce.domain.model.user.UsersModel;
import org.george.ecommerce.exception.InvalidRequestException;
import org.george.ecommerce.repository.UsersRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Optional;
import java.util.function.Function;


@AllArgsConstructor
@Service
public class UsersService {
    final UsersRepository usersRepository;
    final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<UsersModel> pageUsersModel = usersRepository.findAll(pageable);
        Page<UserDTO> pageUserDTO = pageUsersModel.map(new Function<UsersModel, UserDTO>() {
            @Override
            public UserDTO apply(UsersModel usersModel) {
                UserDTO userDTO = new UserDTO();
                modelMapper.map(usersModel, userDTO);
                return userDTO;
            }
        });

        return pageUserDTO ;
    }

    @Transactional
    public UserDTO getUsers(Long userId) {
        if (!usersRepository.existsById(userId)) {
            throw new InvalidRequestException();
        }
        UserDTO userDTO = new UserDTO();
        modelMapper.map(usersRepository.getById(userId),  userDTO);
        return userDTO;
    }

    @Transactional
    public UsersModel save(UserDTO userDTO) {
        UsersModel userToPersist = modelMapper.map(userDTO, UsersModel.class);
        return usersRepository.save(userToPersist);
    }

    @Transactional
    public void delete(Long userId) {
        usersRepository.deleteById(userId);
    }

    @Transactional
    public UsersModel update(Long userId, UserDTO userDTO) {
        if (!usersRepository.existsById(userId)) {
            throw new NotFoundException("User not found");
        }

        Optional<UsersModel> optional = usersRepository.findById(userId);
        if (!optional.isPresent()) {
            throw new InvalidRequestException();
        }

        UsersModel stored = optional.get();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(userDTO, stored);
        return usersRepository.save(stored);
    }
}
