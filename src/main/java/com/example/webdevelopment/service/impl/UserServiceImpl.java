package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.UserDTO;
import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.User;
import com.example.webdevelopment.repositorie.UserRepository;
import com.example.webdevelopment.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User saveUser = userRepository.save(user);
        return modelMapper.map(saveUser, UserDTO.class);
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
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            User updateUser = userRepository.save(user);
            return modelMapper.map(updateUser, UserDTO.class);
        }
        return null;
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

}

