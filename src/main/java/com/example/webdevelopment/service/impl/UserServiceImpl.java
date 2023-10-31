package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.UserDTO;
import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.model.User;
import com.example.webdevelopment.repositorie.UserRepository;
import com.example.webdevelopment.service.UserService;
import com.example.webdevelopment.validation.ValidationUtil;
import com.example.webdevelopment.views.UserViewModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Set<ConstraintViolation<UserDTO>> violations = validationUtil.violations(userDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO updatUser(UUID id, UserDTO userDTO) {
        Set<ConstraintViolation<UserDTO>> violations = validationUtil.violations(userDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setImageUrl(userDTO.getImageUrl());
            User updateUser = userRepository.save(user);
            return modelMapper.map(updateUser, UserDTO.class);
        }
        return null;
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Object[]> getUsersByRole(Role role) {
        return userRepository.findUsersByRole(role);
    }
    @Override
    public UserViewModel getUserViewModelById(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            ModelMapper modelMapper = new ModelMapper();
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return new UserViewModel(userDTO.getUsername(), "your@email.com");
        }
        return null;
    }

    @Override
    public List<UserDTO> getUserByUsername(String username) {
        List<User> users = userRepository.findUserByUsername(username);
        return users.stream().map(user -> modelMapper.map(user,UserDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        Set<ConstraintViolation<UserDTO>> violations = validationUtil.violations(userDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        List<User> usersWithUsername = userRepository.findUserByUsername(userDTO.getUsername());
        if (!usersWithUsername.isEmpty()) {
            throw new RuntimeException("Пользователь с таким именем пользователя уже существует");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setActive(true);
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }
}

