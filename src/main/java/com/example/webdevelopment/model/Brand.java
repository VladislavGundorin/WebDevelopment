package com.example.webdevelopment.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    @Column(name="name")
    private String name;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    @OneToMany(mappedBy = "brands")
    List<Model> models;

    public Brand(String name, LocalDateTime created, LocalDateTime modified, List<Model> models) {
        this.name = name;
        this.created = created;
        this.modified = modified;
        this.models = models;
    }
    public Brand(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", models=" + models +
                ", id=" + id +
                "} " + super.toString();
    }
}
