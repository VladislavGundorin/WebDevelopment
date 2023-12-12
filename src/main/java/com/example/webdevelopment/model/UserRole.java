package com.example.webdevelopment.model;

import com.example.webdevelopment.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "name",unique = true)
    private Role role;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<User> users;

    public UserRole(){

    }

    public UserRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role=" + role +
                ", id=" + id +
                "} " + super.toString();
    }
}
