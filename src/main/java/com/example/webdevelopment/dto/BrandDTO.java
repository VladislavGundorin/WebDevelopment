package com.example.webdevelopment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class BrandDTO {
    private UUID id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BrandDTO(UUID id, String name, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.modified = modified;
    }
    public BrandDTO(){

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

    @Override
    public String toString() {
        return "BrandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
