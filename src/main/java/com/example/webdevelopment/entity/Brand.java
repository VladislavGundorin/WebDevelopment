package com.example.webdevelopment.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    @Column(name = "name")
    private String name;

    protected LocalDateTime created;
    protected LocalDateTime modified;
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
