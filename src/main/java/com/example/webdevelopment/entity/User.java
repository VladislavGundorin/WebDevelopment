package com.example.webdevelopment.entity;

import com.example.webdevelopment.enums.UserRole;
import jakarta.persistence.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private UserRole role;
    private URL imageUrl;
    protected LocalDateTime created;
    protected LocalDateTime modified;
//    @OneToMany
//    @JoinColumn(name = "username",referencedColumnName = "id")
//    private List<Offer> offers;
    @PrePersist
    protected void onCreate(){
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        modified = LocalDateTime.now();
    }

}

