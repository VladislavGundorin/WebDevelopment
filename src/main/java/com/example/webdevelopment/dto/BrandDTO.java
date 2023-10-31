package com.example.webdevelopment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public class BrandDTO {
    private UUID id;
    @NotNull
    @NotEmpty
    @Length(min = 2,message = "поле должно содержать минимум 2 символа")
    private String name;

    public BrandDTO(UUID id, String name) {
        this.id = id;
        this.name = name;

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

    @Override
    public String toString() {
        return "BrandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
