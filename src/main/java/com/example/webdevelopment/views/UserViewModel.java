package com.example.webdevelopment.views;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserViewModel {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Пароль должен содержать не менее 8 символов и содержать по крайней мере одну строчную букву, одну заглавную букву и одну цифру.")
    private String password;

    public UserViewModel(String username, String password) {
        this.username = username;
        this.password = password;
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
}