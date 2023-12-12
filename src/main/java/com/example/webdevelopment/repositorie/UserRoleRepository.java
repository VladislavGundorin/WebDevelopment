package com.example.webdevelopment.repositorie;

import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    @Query("SELECT u.username, u.password, r.role AS roleName " +
            "FROM User u " +
            "JOIN u.role r " +
            "WHERE u.isActive = true")
    List<Object[]> findActiveUsersWithRoles();

    UserRole findByRole(Role role);

}


