package com.example.webdevelopment.entity;

import com.example.webdevelopment.enums.Engine;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.math.BigDecimal;


@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{
    @Lob
    private String description;
    private Engine engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private Integer year;
    private String model;
    private String seller;
}
