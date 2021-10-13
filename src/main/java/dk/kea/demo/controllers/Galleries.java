package dk.kea.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kea.demo.models.Artist;
import dk.kea.demo.models.Gallery;
import dk.kea.demo.models.Painting;
import dk.kea.demo.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Galleries {

    @Autowired
    GalleryRepository galleries;


    @GetMapping("/galleries/{id}")
    public Gallery getGalleryById(@PathVariable Long id) {
        return galleries.findById(id).get();
    }


    @PostMapping("/galleries")
    public Gallery addGallery(@RequestBody Gallery newGallery) {
        return galleries.save(newGallery);
    }


    @PutMapping
    public String updateGallery(@PathVariable Long id, @RequestBody Gallery galleryToUpdate) {
        if(galleries.existsById(id)) {
            galleryToUpdate.setId(id);
            galleries.save(galleryToUpdate);
            return "Gallery was created";
        } else {
            return "Gallery not found";
        }
    }

    @PatchMapping("/galleries/{id}")
    public String patchGallery(@PathVariable Long id, @RequestBody Gallery galleryToUpdate) {
        return galleries.findById(id).map( foundGallery -> {
            if(galleryToUpdate.getOwner() != null) foundGallery.setOwner(galleryToUpdate.getOwner());
            if(galleryToUpdate.getName() != null) foundGallery.setName(galleryToUpdate.getName());
            if(galleryToUpdate.getLocation() != null) foundGallery.setLocation(galleryToUpdate.getLocation());
            if(galleryToUpdate.getSquareFeet() != 0) foundGallery.setSquareFeet(galleryToUpdate.getSquareFeet());
            galleries.save(foundGallery);
            return "Gallery updated";
        }).orElse("Gallery not found");
    }

    @DeleteMapping("/galleries/{id}")
    public void deleteGalleryById(@PathVariable Long id) {
        galleries.deleteById(id);
    }

}
