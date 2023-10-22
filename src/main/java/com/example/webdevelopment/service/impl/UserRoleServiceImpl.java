package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.UserRoleDTO;
import com.example.webdevelopment.model.UserRole;
import com.example.webdevelopment.repositorie.UserRoleRepository;
import com.example.webdevelopment.service.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleDTO createUderRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = modelMapper.map(userRoleDTO, UserRole.class);
        UserRole saveUserRole = userRoleRepository.save(userRole);
        return modelMapper.map(saveUserRole, UserRoleDTO.class);
    }

    @Override
    public List<UserRoleDTO> getAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        return userRoles.stream().map(userRole -> modelMapper.map(userRole, UserRoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRoleDTO> getUserRoleByID(UUID id) {
        Optional<UserRole> optionalUserRoleDTO = userRoleRepository.findById(id);
        return optionalUserRoleDTO.map(userRole -> modelMapper.map(userRole, UserRoleDTO.class));
    }

    @Override
    public UserRoleDTO updateUserRoleById(UUID id, UserRoleDTO userRoleDTO) {
        Optional<UserRole> optionalUserRole = userRoleRepository.findById(id);
        if (optionalUserRole.isPresent()) {
            UserRole userRole = optionalUserRole.get();
            userRole.setRole(userRoleDTO.getRole());
            UserRole updateUserRole = userRoleRepository.save(userRole);
            return modelMapper.map(updateUserRole, UserRoleDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUserRole(UUID id) {
        userRoleRepository.deleteById(id);
    }
}
