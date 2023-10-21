package com.example.webdevelopment.model;

import com.example.webdevelopment.enums.RoleType;
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
    private RoleType role;
    private URL imageUrl;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    @OneToMany(mappedBy = "user")
    private List<Offer> offers;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRoles;

    public User(String username, String password, String firstName, String lastName, boolean isActive, RoleType role, URL imageUrl, LocalDateTime created, LocalDateTime modified, List<Offer> offers, UserRole userRoles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.role = role;
        this.imageUrl = imageUrl;
        this.created = created;
        this.modified = modified;
        this.offers = offers;
        this.userRoles = userRoles;
    }
    public User(){

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

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public UserRole getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRole userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                ", imageUrl=" + imageUrl +
                ", created=" + created +
                ", modified=" + modified +
                ", offers=" + offers +
                ", userRoles=" + userRoles +
                ", id=" + id +
                "} " + super.toString();
    }
}

