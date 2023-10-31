package com.example.webdevelopment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String username;
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Пароль должен содержать не менее 8 символов и содержать по крайней мере одну строчную букву, одну заглавную букву и одну цифру.")
    private String password;
    @NotBlank(message = "Поле имени не может быть пустым")
    private String firstName;
    @NotBlank(message = "Поле фамилии не может бывть пустым")
    private String lastName;
    private boolean isActive;
    @Pattern(regexp = "^(http|https)://[a-zA-Z0-9./]+$", message = "Неверный URL формат")
    private String imageUrl;
    private UserRoleDTO role;

    private String email;

    public UserDTO(){
        this.firstName = "DefaultFirstName";
        this.lastName = "DefaultLastName";
    }

    public UserDTO(UUID id, String username, String password, String firstName, String lastName, boolean isActive, String imageUrl, UserRoleDTO role, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.role = role;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
                '}';
    }
}
