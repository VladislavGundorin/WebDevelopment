package com.example.webdevelopment.model;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(name = "id",length = 20)
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

}
