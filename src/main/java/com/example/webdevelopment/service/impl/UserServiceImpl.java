package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.UserDTO;
import com.example.webdevelopment.model.User;
import com.example.webdevelopment.repositorie.UserRepository;
import com.example.webdevelopment.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User saveUser = userRepository.save(user);
        return modelMapper.map(saveUser, UserDTO.class);
    }
}
