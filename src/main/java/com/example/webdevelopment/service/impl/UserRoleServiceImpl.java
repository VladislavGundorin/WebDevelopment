package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.UserRoleDTO;
import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.UserRole;
import com.example.webdevelopment.repositorie.UserRoleRepository;
import com.example.webdevelopment.service.UserRoleService;
import com.example.webdevelopment.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class UserRoleServiceImpl implements UserRoleService {
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper, UserRoleRepository userRoleRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.validationUtil = validationUtil;
    }
    @Override
    @CacheEvict(value = "userRoles", allEntries = true)
    public UserRoleDTO createUserRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = modelMapper.map(userRoleDTO, UserRole.class);
        UserRole saveUserRole = userRoleRepository.save(userRole);
        return modelMapper.map(saveUserRole, UserRoleDTO.class);
    }

    @Override
    @Cacheable(value = "userRoles")
    public List<UserRoleDTO> getAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        return userRoles.stream().map(userRole -> modelMapper.map(userRole, UserRoleDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    @Cacheable(value = "userRoles", key = "#id")
    public Optional<UserRoleDTO> getUserRoleByID(UUID id) {
        Optional<UserRole> optionalUserRoleDTO = userRoleRepository.findById(id);
        return optionalUserRoleDTO.map(userRole -> modelMapper.map(userRole, UserRoleDTO.class));
    }
    @Override
    @CacheEvict(value = "userRoles", key = "#id")
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
    @CacheEvict(value = "userRoles", key = "#id")
    public void deleteUserRole(UUID id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "activeUsersWithRoles")
    public List<Object[]> getActiveUsersWithRoles() {
        return userRoleRepository.findActiveUsersWithRoles();
    }

    @Override
    @Cacheable(value = "userRolesByRole", key = "#role")
    public UserRole getByRole(Role role) {
        return userRoleRepository.findByRole(role);
    }

}
