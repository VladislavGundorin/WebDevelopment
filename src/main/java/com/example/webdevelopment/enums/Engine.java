package com.example.webdevelopment.enums;

public enum Engine {
    GASOLINE(1), DIESEL(2), ELECTRIC(3), HYBRID(4);

    private final int value;

    Engine(int value){
        this.value = value;
    }
}
