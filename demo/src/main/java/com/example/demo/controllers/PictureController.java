package com.example.demo.controllers;

import com.example.demo.models.Picture;
import com.example.demo.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping
    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Picture> getPictureById(@PathVariable Long id) {
        Picture picture = pictureService.getPictureById(id);

        if (picture != null) {
            return new ResponseEntity<>(picture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Picture> createPicture(@RequestBody Picture picture) {
        Picture createdPicture = pictureService.createPicture(picture);
        return new ResponseEntity<>(createdPicture, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Picture> updatePicture(@PathVariable Long id, @RequestBody Picture updatedPicture) {
        Picture updated = pictureService.updatePicture(id, updatedPicture);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePicture(@PathVariable Long id) {
        pictureService.deletePicture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
