package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    String description;
    String blob;
    String category;

    public Picture(String description, String category, String blob) {
        this.description = description;
        this.category = category;
        this.blob = blob;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBlob() {
        return blob;
    }
    public void setBlob(String blob) {
        this.blob = blob;
    }

    public Long getId() {
        return id;
    }
}