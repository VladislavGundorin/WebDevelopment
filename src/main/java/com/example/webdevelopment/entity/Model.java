package com.example.webdevelopment.entity;

import com.example.webdevelopment.enums.Category;
import jakarta.persistence.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    private String name;
//    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "category",length = 11)
    private URL imageUrl;
    private Integer startYear;
    private Integer endYear;
    @Column(name = "brand_model")
    private String brand;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    @OneToMany(mappedBy = "model_id")
    List<Offer> offer;
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
