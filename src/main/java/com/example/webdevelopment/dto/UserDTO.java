package com.example.webdevelopment.dto;

import com.example.webdevelopment.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String imageUrl;
    private UserRoleDTO role;

    private LocalDateTime created;
    private LocalDateTime modified;

    public UserDTO(){

    }

    public UserDTO(UUID id, String username, String password, String firstName, String lastName, boolean isActive, String imageUrl, UserRoleDTO role, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.role = role;
        this.created = created;
        this.modified = modified;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserRoleDTO getRole() {
        return role;
    }

    public void setRole(UserRoleDTO role) {
        this.role = role;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", imageUrl='" + imageUrl + '\'' +
                ", role=" + role +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
