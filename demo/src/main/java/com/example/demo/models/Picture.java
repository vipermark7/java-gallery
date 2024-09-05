package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pictures")
@Setter
@Getter
@Builder
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    byte[] blob;
    //TODO: implement categories
    //List<String> categories  = new ArrayList<>();

    public Picture(String description, byte[] blob) {
        this.description = description;
        //this.categories = categories;
        this.blob = blob;
    }

    public Picture() {
    }
}