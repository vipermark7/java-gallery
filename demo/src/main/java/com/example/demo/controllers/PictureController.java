package com.example.demo.controllers;

import com.example.demo.models.Picture;
import com.example.demo.services.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/all")
    public List<Picture> getAllPictures() {
        // using a separate variable here so that our Thymeleaf page for showing all pictures
        // can render the pictures, without directly calling getAllPictures()
        return pictureService.getAllPictures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Picture> getPictureById(@PathVariable Long id) {
        var picture = pictureService.getPictureById(id);
        if (picture != null && picture.isPresent()) {
            return new ResponseEntity<>(picture.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Picture> createPicture(@RequestBody Picture picture) {
        var createdPicture = pictureService.createPicture(picture);
        return new ResponseEntity<>(createdPicture, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Picture> updatePicture(
            @PathVariable Long id,
            @RequestBody Picture updatedPicture) {
        var updated = pictureService.updatePicture(id, updatedPicture);

        if (updated != null && updated.isPresent()) {
            return new ResponseEntity<>(updated.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePicture(@PathVariable Long id) {
        pictureService.deletePicture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
