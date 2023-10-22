package com.example.webdevelopment.model;

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
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    @Column(name = "brand_model", insertable = false, updatable = false)
    private String brand;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    @OneToMany(mappedBy = "models")
    List<Offer> offers;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brands;

    public Model(){

    }

    public Model(String name, Category category, String imageUrl, Integer startYear, Integer endYear, String brand, LocalDateTime created, LocalDateTime modified, List<Offer> offers, Brand brands) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
        this.created = created;
        this.modified = modified;
        this.offers = offers;
        this.brands = brands;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Brand getBrands() {
        return brands;
    }

    public void setBrands(Brand brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", imageUrl=" + imageUrl +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", brand='" + brand + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", offers=" + offers +
                ", brands=" + brands +
                ", id=" + id +
                "} " + super.toString();
    }
}
