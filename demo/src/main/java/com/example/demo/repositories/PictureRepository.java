package com.example.demo.repositories;

import com.example.demo.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    // You can add custom query methods if needed
}
