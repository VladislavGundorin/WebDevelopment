package com.example.webdevelopment.entity;

import com.example.webdevelopment.enums.RoleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    private RoleType role;
    @OneToMany(mappedBy = "userRoles")
    List<User> users;

}
