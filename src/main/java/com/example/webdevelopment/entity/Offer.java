package com.example.webdevelopment.entity;

import com.example.webdevelopment.enums.Engine;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;


@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{
    @Lob
    private String description;
    private Engine engine;
    private URL imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private Integer year;
    private String model;
    private String seller;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model models;
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

