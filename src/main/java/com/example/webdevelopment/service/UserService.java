package com.example.webdevelopment.service;

import com.example.webdevelopment.dto.UserDTO;
import com.example.webdevelopment.dto.UserRoleDTO;
import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserDTO createUser (UserDTO userDTO);

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(UUID id);

    UserDTO updatUser(UUID id, UserDTO userDTO);

    void deleteUserById(UUID id);

    List<Object[]> findUsersByRole(@Param("roleValue") Role role);
}
