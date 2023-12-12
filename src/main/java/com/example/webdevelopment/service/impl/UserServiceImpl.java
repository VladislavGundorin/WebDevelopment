package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.UserDTO;
import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.model.User;
import com.example.webdevelopment.model.UserRole;
import com.example.webdevelopment.repositorie.OfferRepository;
import com.example.webdevelopment.repositorie.UserRepository;
import com.example.webdevelopment.repositorie.UserRoleRepository;
import com.example.webdevelopment.service.UserRoleService;
import com.example.webdevelopment.service.UserService;
import com.example.webdevelopment.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final OfferRepository offerRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserRoleRepository userRoleRepository,UserRoleService userRoleService,PasswordEncoder passwordEncoder,OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userRoleRepository = userRoleRepository;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.offerRepository = offerRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        if (user.getRole() != null) {
            UserRole existingRole = userRoleRepository.findByRole(user.getRole().getRole());

            if (existingRole != null) {
                user.setRole(existingRole);
            } else {
                UserRole savedRole = userRoleRepository.save(user.getRole());
                user.setRole(savedRole);
            }
        }

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
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setImageUrl(userDTO.getImageUrl());
            if (userDTO.getRole() != null) {
                user.setRole(user.getRole());
            }
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
    public User getUserByUsername(String username) {
        Optional<User> users = userRepository.findUserByUsername(username);
        return users.get();
    }
    @Override
    public List<User> getByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Offer> allUserOffers(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return offerRepository.findBySeller(user);
        }
        return Collections.emptyList();
    }
}


