package com.example.demo.repositories;

import com.example.demo.models.Picture;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PictureRepository extends JpaRepository<Picture, Long> {
}
