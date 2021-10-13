package dk.kea.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kea.demo.models.Artist;
import dk.kea.demo.models.Painting;
import dk.kea.demo.repositories.PaintingRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Paintings {

    @Autowired
    PaintingRepositroy paintings;

    @GetMapping("/paintings")
    public Iterable<Painting> getPainting() {
        return paintings.findAll();
    }

    @GetMapping("/paintings/{id}")
    public Painting getPaintingById(@PathVariable Long id) {
        return paintings.findById(id).get();
    }

    @PostMapping("/paintings")
    public Painting addPaintings(@RequestBody Painting newPainting) {
        return paintings.save(newPainting);
    }


    @PutMapping("/paintings/{id}")
    public String updatePaintingById(@PathVariable Long id, @RequestBody Painting paintingToUpdate) {
        if(paintings.existsById(id)) {
            paintingToUpdate.setId(id);
            paintings.save(paintingToUpdate);
            return "Painting was updated";
        } else {
            return "Painting not found";
        }
    }

    @PatchMapping("/paintings/{id}")
    public String patchPainting(@PathVariable Long id, @RequestBody Painting paintingToupdate) {
        return paintings.findById(id).map( foundPainting -> {
            if(paintingToupdate.getArtist() != null) foundPainting.setArtist(paintingToupdate.getArtist());
            if(paintingToupdate.getPrice() != 0) foundPainting.setPrice(paintingToupdate.getPrice());
            if(paintingToupdate.getTitle() != null) foundPainting.setTitle(paintingToupdate.getTitle());
            if(paintingToupdate.getGenre() != null) foundPainting.setGenre(paintingToupdate.getGenre());
            if(paintingToupdate.getYear() != 0) foundPainting.setYear(paintingToupdate.getYear());
            paintings.save(foundPainting);
            return "Painting updated";
        }).orElse("Painting not found");
    }


    @DeleteMapping("/paintings/{id}")
    public void deletePaintingById(@PathVariable Long id) {
        paintings.deleteById(id);
    }

}
