package com.example.webdevelopment.views;


import com.example.webdevelopment.dto.BrandDTO;
import com.example.webdevelopment.model.BaseEntity;

import java.util.UUID;

public class OfferViewModel extends BaseEntity {
    private UUID id;
    private String imageUrl;
    private String brandName;
    private String modelName;
    private int price;

    public OfferViewModel(UUID id,String brandName, String modelName, int price, String imageUrl) {
        this.id = id;
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public UUID getId(){
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "OfferViewModel{" +
                "imageUrl='" + imageUrl + '\'' +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", price=" + price +
                '}';
    }
}
