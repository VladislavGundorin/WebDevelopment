package com.example.webdevelopment.views;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class OfferViewModel {
    @Pattern(regexp = "^(http|https)://[a-zA-Z0-9./]+$", message = "Неверный URL формат")
    private String imageUrl;
    @Positive(message = "пробег должен быть положительным числом")
    private int mileage;
    @Positive(message = "Цена должна быть положительным числом")
    private int price;

    public OfferViewModel(String imageUrl, int mileage, int price) {
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
