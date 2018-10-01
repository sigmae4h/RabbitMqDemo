package com.example.producer.model;

public class Data {
    private String key;
    private String color;

    public Data() {
    }

    public Data(String key, String color) {
        this.key = key;
        this.color = color;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("Data {key: %s, color: %s}", key, color);
    }
}
