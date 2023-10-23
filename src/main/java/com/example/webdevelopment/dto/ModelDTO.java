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
    private BrandDTO brand;


    public ModelDTO(UUID id, String name, Category category, int startYear, int endYear, LocalDateTime created, LocalDateTime modified, String imageUrl, BrandDTO brand) {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
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
                ", brand=" + brand +
                '}';
    }
}
