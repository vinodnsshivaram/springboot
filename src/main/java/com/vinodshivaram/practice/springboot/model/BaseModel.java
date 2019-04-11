package com.vinodshivaram.practice.springboot.model;

import java.util.UUID;

import static com.vinodshivaram.practice.springboot.support.Utilities.getCurrentDateTimeFormatted;

public class BaseModel {
    private UUID id;
    private String createdAt;
    private String updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedId) {
        this.updatedAt = updatedId;
    }

    public void buildDateTimeAndId() {
        setId(UUID.randomUUID());
        setCreatedAt(getCurrentDateTimeFormatted());
        setUpdatedAt(getCurrentDateTimeFormatted());
    }

    public void updateUpdatedAt() {
        setUpdatedAt(getCurrentDateTimeFormatted());
    }
}
