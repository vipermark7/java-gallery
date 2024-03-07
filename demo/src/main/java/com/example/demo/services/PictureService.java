package com.example.demo.services;

import com.example.demo.models.Picture;
import com.example.demo.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    public Picture getPictureById(Long id) {
        return pictureRepository.findById(id).orElse(null);
    }

    public Picture createPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    public Picture updatePicture(Long id, Picture updatedPicture) {
        Picture existingPicture = pictureRepository.findById(id).orElse(null);

        if (existingPicture != null) {
            existingPicture.setName(updatedPicture.getName());
            existingPicture.setDescription(updatedPicture.getDescription());
            existingPicture.setUrl(updatedPicture.getUrl());

            return pictureRepository.save(existingPicture);
        }
        return null;
    }

    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);
    }
}
