package com.harryseong.microservices.controller;

import com.harryseong.microservices.MicroservicesApplication;
import com.harryseong.microservices.domain.Artist;
import com.harryseong.microservices.domain.ArtistDTO;
import com.harryseong.microservices.domain.Song;
import com.harryseong.microservices.repository.ArtistRepository;
import com.harryseong.microservices.repository.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/music")
public class MusicControllerV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(MicroservicesApplication.class);

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private SongRepository songRepository;

    @GetMapping("/artist/{id}")
    public Artist getArtist(@PathVariable long id) { return artistRepository.findById(id); }

    @PostMapping("/artist")
    public void createArtist(@RequestBody ArtistDTO artistDTO) {
        artistRepository.save(new Artist(artistDTO.getName(), new Date()));
    }

    @DeleteMapping("/artist/{id}")
    public void deleteArtist(@PathVariable long id) {
        artistRepository.deleteById(id);
    }

    @GetMapping("/artist/all")
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/song")
    public Song getSong(@RequestParam(value="id", defaultValue="1") long id) {
        return songRepository.findById(id);
    }

    @GetMapping("/song/all")
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
