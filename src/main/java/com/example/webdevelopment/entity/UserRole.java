package com.example.webdevelopment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "userRoles")
public class UserRole extends BaseEntity{
    private UserRole roleName;
}
