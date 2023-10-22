package com.example.webdevelopment.dto;

import com.example.webdevelopment.enums.Role;

import java.util.UUID;

public class UserRoleDTO {
    private UUID id;
    private Role role;

    public UserRoleDTO(UUID id, Role role) {
        this.id = id;
        this.role = role;
    }

    public UserRoleDTO() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
