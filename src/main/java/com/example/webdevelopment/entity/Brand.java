package com.example.webdevelopment.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    private String name;

    protected LocalDateTime created;
    protected LocalDateTime modified;
    @PrePersist
    protected void onCreate(){
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
    }
    @OneToMany(mappedBy = "brands")
    List<Model> models;

    @PreUpdate
    protected void onUpdate(){
        modified = LocalDateTime.now();
    }
}