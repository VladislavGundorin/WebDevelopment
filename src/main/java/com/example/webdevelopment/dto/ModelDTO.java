package com.example.webdevelopment.dto;

import com.example.webdevelopment.enums.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class ModelDTO {
    private UUID id;
    private String name;
    private Category category;
    private int startYear;
    private int endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String imageUrl;
    private String brand;

    public ModelDTO(UUID id, String name, Category category, int startYear, int endYear, LocalDateTime created, LocalDateTime modified, String imageUrl, String brand) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
        this.imageUrl = imageUrl;
        this.brand = brand;
    }
    public ModelDTO(){

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "ModelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", created=" + created +
                ", modified=" + modified +
                ", imageUrl='" + imageUrl + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
