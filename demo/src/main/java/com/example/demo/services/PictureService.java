package com.example.demo.services;

import com.example.demo.models.Picture;
import com.example.demo.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("/api/pictures")
public class PictureService {

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Autowired
    private final PictureRepository pictureRepository;

    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }
    @GetMapping
    public Picture getPictureById(Long id) {
        return pictureRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Picture createPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    public Picture updatePicture(Long id, Picture updatedPicture) {
        Picture existingPicture = pictureRepository.findById(id).orElse(null);

        if (existingPicture != null) {
            existingPicture.setDescription(updatedPicture.getDescription());
            existingPicture.setBlob(updatedPicture.getBlob());

            return pictureRepository.save(existingPicture);
        } else {
            return null;
        }
    }

    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }
}
