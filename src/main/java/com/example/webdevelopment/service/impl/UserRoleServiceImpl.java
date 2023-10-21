package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.UserRoleDTO;
import com.example.webdevelopment.model.UserRole;
import com.example.webdevelopment.repositorie.UserRoleRepository;
import com.example.webdevelopment.service.UserRoleService;
import com.example.webdevelopment.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper,UserRoleRepository userRoleRepository){
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }
    @Override
    public UserRoleDTO createUderRole(UserRoleDTO userRoleDTO){
        UserRole userRole = modelMapper.map(userRoleDTO, UserRole.class);
        UserRole saveUserRole = userRoleRepository.save(userRole);
        return modelMapper.map(saveUserRole,UserRoleDTO.class);
    }
}
