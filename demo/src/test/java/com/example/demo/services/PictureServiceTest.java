package com.example.demo.services;

import com.example.demo.models.Picture;
import com.example.demo.repositories.PictureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PictureServiceTest {

    @Mock
    private PictureRepository pictureRepository;

    @InjectMocks
    private PictureService pictureService;

    @Mock
    private Logger logger;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        // Mock the static LoggerFactory to return our mock logger
    }

    @Test
    public void testConvertBase64ToBytes() {
        String base64String = "SGVsbG8sIFdvcmxkIQ=="; // "Hello, World!" in base64
        byte[] expectedBytes = "Hello, World!".getBytes();

        byte[] result = PictureService.convertBase64ToBytes(base64String);

        assertArrayEquals(expectedBytes, result);
    }

    @Test
    public void testGetAllPictures() {
        List<Picture> pictures = new ArrayList<>();
        Picture picture = new Picture();
        picture.setId(1L);
        pictures.add(picture);

        when(pictureRepository.findAll())
                .thenReturn(pictures);

        List<Picture> result = pictureService.getAllPictures();

        assertEquals(1, result.size());
        assertEquals(picture, result.get(0));
    }

    @Test
    public void testGetPictureById() {
        Picture picture = new Picture();
        picture.setId(1L);

        when(pictureRepository.findById(1L))
                .thenReturn(Optional.of(picture));

        Picture result = pictureService.getPictureById(1L);

        assertNotNull(result);
        assertEquals(picture, result);
    }

    @Test
    public void testGetPictureById_NotFound() {
        when(pictureRepository.findById(1L))
                .thenReturn(Optional.empty());

        Picture result = pictureService.getPictureById(1L);

        assertNull(result);
    }

    @Test
    public void testCreatePicture() {
        Picture picture = new Picture();
        picture.setId(1L);

        when(pictureRepository.save(picture))
                .thenReturn(picture);

        Picture result = pictureService.createPicture(picture);

        assertNotNull(result);
        assertEquals(picture, result);
    }

    @Test
    public void testUpdatePicture() {
        Picture existingPicture = new Picture();
        existingPicture.setId(1L);
        existingPicture.setDescription("Old Description");

        Picture updatedPicture = new Picture();
        updatedPicture.setDescription("New Description");
        updatedPicture.setBlob(new byte[]{1, 2, 3});

        when(pictureRepository.findById(1L))
                .thenReturn(Optional.of(existingPicture));

        when(pictureRepository.save(any(Picture.class)))
                .thenReturn(updatedPicture);

        Picture result = pictureService.updatePicture(1L, updatedPicture);

        assertNotNull(result);
        assertEquals("New Description", result.getDescription());
        assertArrayEquals(new byte[]{1, 2, 3}, result.getBlob());
    }

    @Test
    public void testUpdatePicture_NotFound() {
        Picture updatedPicture = new Picture();
        updatedPicture.setDescription("New Description");

        when(pictureRepository.findById(1L))
                .thenReturn(Optional.empty());

        Picture result = pictureService.updatePicture(1L, updatedPicture);

        assertNull(result);
        verify(logger).error("Couldn't find/update picture with ID: {}", 1L);
    }

    @Test
    public void testDeletePicture() {
        doNothing().when(pictureRepository).deleteById(1L);

        pictureService.deletePicture(1L);

        verify(pictureRepository).deleteById(1L);
    }

    @Test
    public void testDeletePicture_Exception() {
        doThrow(new IllegalArgumentException("Invalid ID")).when(pictureRepository).deleteById(1L);

        pictureService.deletePicture(1L);

        verify(pictureRepository).deleteById(1L);
        verify(logger).error("Invalid ID");
    }
}
