package dk.kea.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kea.demo.models.Artist;
import dk.kea.demo.models.Painting;
import dk.kea.demo.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Artists {

    @Autowired
    ArtistRepository artists;

    @GetMapping("/artists")
    public Iterable<Artist> getArtists() {
        return artists.findAll();
    }

    @GetMapping("/artists/{id}")
    public Artist getArtists(@PathVariable Long id) {
        return artists.findById(id).get();
    }

    @PostMapping("/artists")
    public Artist addArtist(@RequestBody Artist newArtist) {
        return artists.save(newArtist);
    }

    @PutMapping("/galleries/{id}")
    public String updateArtist(@PathVariable Long id, @RequestBody Artist artistToUpdate){
    if(artists.existsById(id)) {
        artistToUpdate.setId(id);
        artists.save(artistToUpdate);
        return "Gallery was updated";
    } else {
        return "Gallery not found";
    }
    }

    @PatchMapping("/artists/{id}")
    public String patchArtist(@PathVariable Long id, @RequestBody Artist artistToUpdate) {
        return artists.findById(id).map( foundArtist -> {
            if(artistToUpdate.getName() != null) foundArtist.setName(artistToUpdate.getName());
            if(artistToUpdate.getAge() != 0) foundArtist.setAge(artistToUpdate.getAge());
            if(artistToUpdate.getBirthDate() != null) foundArtist.setBirthDate(artistToUpdate.getBirthDate());
            if(artistToUpdate.getGender() != null) foundArtist.setGender(artistToUpdate.getGender());
            if(artistToUpdate.getNationality() != null) foundArtist.setNationality(artistToUpdate.getNationality());
            if(artistToUpdate.getPrimaryStyle() != null) foundArtist.setPrimaryStyle(artistToUpdate.getPrimaryStyle());
            artists.save(foundArtist);
            return "Artist updated";
        }).orElse("Artist not found");
    }

    @DeleteMapping("/artists/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artists.deleteById(id);
    }


}
