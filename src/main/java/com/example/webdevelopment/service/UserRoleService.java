package com.example.webdevelopment.service;

import com.example.webdevelopment.dto.UserRoleDTO;
import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.UserRole;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService {
    UserRoleDTO createUserRole(UserRoleDTO userRoleDTO);

    List<UserRoleDTO> getAllUserRoles();

    Optional<UserRoleDTO> getUserRoleByID(UUID id);

    UserRoleDTO updateUserRoleById(UUID id,UserRoleDTO userRoleDTO);

    void deleteUserRole (UUID id);

    List<Object[]> getActiveUsersWithRoles();

    UserRole getByRole(Role role);


}
