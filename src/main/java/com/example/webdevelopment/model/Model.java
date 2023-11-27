package com.example.webdevelopment.model;

import com.example.webdevelopment.enums.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "models")
public class Model extends BaseEntity implements ImageUrlProvider{
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category")
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private int endYear;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "model")
    List<Offer> offers;

    @ManyToOne
    @JoinColumn(name = "brand_id",referencedColumnName = "id")
    private Brand brand;

    public Model(){

    }

    public Model(String name,String imageUrl, Category category, int startYear, int endYear, LocalDateTime created, LocalDateTime modified, Brand brand) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
        this.brand = brand;
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

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", created=" + created +
                ", modified=" + modified +
                ", brand=" + brand +
                ", id=" + id +
                "} " + super.toString();
    }

    @Override
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
