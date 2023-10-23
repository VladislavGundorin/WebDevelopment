package com.example.webdevelopment.service;

import com.example.webdevelopment.dto.UserRoleDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService {
    UserRoleDTO createUderRole (UserRoleDTO userRoleDTO);

    List<UserRoleDTO> getAllUserRoles();

    Optional<UserRoleDTO> getUserRoleByID(UUID id);

    UserRoleDTO updateUserRoleById(UUID id,UserRoleDTO userRoleDTO);

    void deleteUserRole (UUID id);

    List<Object[]> getActiveUsersWithRoles();



}
