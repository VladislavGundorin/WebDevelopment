package com.example.webdevelopment.model;

import com.example.webdevelopment.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @OneToMany(mappedBy = "userRoles",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<User> users;

    public UserRole(Role role) {
        this.role = role;
    }
    public UserRole(){

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
