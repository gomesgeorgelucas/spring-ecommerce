package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.dto.user.UserDTO;
import org.george.ecommerce.domain.model.UsersModel;
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
public class UsersService implements IUsersService {
    final UsersRepository usersRepository;
    final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
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
    @Override
    public UserDTO getUserById(Long userId) {
        if (!usersRepository.existsById(userId)) {
            throw new InvalidRequestException();
        }
        UserDTO userDTO = new UserDTO();
        modelMapper.map(usersRepository.getById(userId),  userDTO);
        return userDTO;
    }

    @Transactional
    @Override
    public UsersModel createUser(UserDTO userDTO) {
        UsersModel userToPersist = modelMapper.map(userDTO, UsersModel.class);
        return usersRepository.save(userToPersist);
    }

    @Transactional
    @Override
    public UsersModel updateUser(Long userId, UserDTO userDTO) {
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

    @Transactional
    @Override
    public void deleteUser(UserDTO userDTO)
    {
        if (usersRepository.findByUserLogin(userDTO.getUserLogin()).isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UsersModel usersModel = usersRepository.findByUserLogin(userDTO.getUserLogin()).get();
        usersRepository.deleteById(usersModel.getUserId());
    }


}
