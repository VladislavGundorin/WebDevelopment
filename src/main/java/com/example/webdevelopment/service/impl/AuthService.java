package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.User;
import com.example.webdevelopment.repositorie.UserRepository;
import com.example.webdevelopment.repositorie.UserRoleRepository;
import com.example.webdevelopment.service.UserRoleService;
import com.example.webdevelopment.service.UserService;
import com.example.webdevelopment.views.UserRegistrationViewModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;
    private ModelMapper modelMapper;
    private UserRoleService userRoleService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper,UserRoleService userRoleService,UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.userService=userService;
        this.userRoleService=userRoleService;
    }


    public void registerUser(UserRegistrationViewModel userRegistrationViewModel) {
        User user= modelMapper.map(userRegistrationViewModel, User.class);
        user.setRole(userRoleService.getByRole(Role.ADMIN));
        user.setActive(true);
        user.setImageUrl("blank");
        user.setPassword(passwordEncoder.encode(userRegistrationViewModel.getPassword()));
        userRepository.saveAndFlush(user);
    }
    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {

        }
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}