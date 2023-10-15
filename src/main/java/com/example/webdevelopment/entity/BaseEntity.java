package com.example.webdevelopment.entity;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(name = "id",length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected UUID id;

}
