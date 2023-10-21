package com.example.webdevelopment.dto;

import com.example.webdevelopment.enums.RoleType;

import java.util.UUID;

public class UserRoleDTO {
    private UUID id;
    private RoleType roleType;

    public UserRoleDTO(UUID id, RoleType roleType) {
        this.id = id;
        this.roleType = roleType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "id=" + id +
                ", roleType=" + roleType +
                '}';
    }
}
