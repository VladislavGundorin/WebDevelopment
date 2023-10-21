package com.example.webdevelopment.model;

import com.example.webdevelopment.enums.RoleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    private RoleType role;
    @OneToMany(mappedBy = "userRoles")
    List<User> users;

    public UserRole(RoleType role) {
        this.role = role;
    }
    public UserRole(){

    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role=" + role +
                ", users=" + users +
                ", id=" + id +
                "} " + super.toString();
    }
}
