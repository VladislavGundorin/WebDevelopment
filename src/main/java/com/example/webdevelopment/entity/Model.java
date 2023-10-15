package com.example.webdevelopment.entity;

import com.example.webdevelopment.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    private String brand;
}
