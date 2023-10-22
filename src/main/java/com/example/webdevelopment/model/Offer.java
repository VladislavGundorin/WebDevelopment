package com.example.webdevelopment.model;

import com.example.webdevelopment.enums.Engine;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;


@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{
    private String description;
    private Engine engine;
    private String imageUrl;
    private Integer mileage;
    private int price;
    private Integer year;
    private String model;
    private String seller;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model models;

     public Offer(){

     }
    public Offer(String description, Engine engine, String imageUrl, Integer mileage, int price, Integer year, String model, String seller, LocalDateTime created, LocalDateTime modified, User user, Model models) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.year = year;
        this.model = model;
        this.seller = seller;
        this.created = created;
        this.modified = modified;
        this.user = user;
        this.models = models;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Model getModels() {
        return models;
    }

    public void setModels(Model models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "description='" + description + '\'' +
                ", engine=" + engine +
                ", imageUrl=" + imageUrl +
                ", mileage=" + mileage +
                ", price=" + price +
                ", year=" + year +
                ", model='" + model + '\'' +
                ", seller='" + seller + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", user=" + user +
                ", models=" + models +
                ", id=" + id +
                "} " + super.toString();
    }
}

