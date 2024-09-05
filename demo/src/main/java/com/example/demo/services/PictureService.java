package com.example.demo.services;

import com.example.demo.models.Picture;
import com.example.demo.repositories.PictureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.List;

@Service
@RequestMapping("/api/pictures")
public class PictureService {

    private final PictureRepository pictureRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public static byte[] convertBase64ToBytes(String base64String) {
        return Base64
                .getDecoder()
                .decode(base64String);
    }

    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @GetMapping
    public Picture getPictureById(Long id) {
        return pictureRepository
                .findById(id)
                .orElse(null);
    }

    @PostMapping
    public Picture createPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    public Picture updatePicture(Long id, Picture picture) {
        Picture existing = pictureRepository
                .findById(id)
                .orElse(null);

        if (existing != null) {
            var updated = Picture
                    .builder()
                    .description(picture.getDescription())
                    .blob(picture.getBlob())
                    .build();

            return pictureRepository.save(updated);
        } else {
            logger.error("Couldn't find/update picture with ID: {}", id);
            return null;
        }
    }

    public void deletePicture(Long id) {
        try {
            pictureRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }
    }
}
