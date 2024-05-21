package com.example.hotelmanagement.storage;

import org.springframework.stereotype.Component;

@Component
public class StorageProperties {
    private String location = "src/main/resources/static/img";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}