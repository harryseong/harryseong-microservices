package com.harryseong.microservices.controller;

import com.harryseong.microservices.domain.Artist;
import com.harryseong.microservices.domain.Greeting;
import com.harryseong.microservices.domain.Song;
import com.harryseong.microservices.repository.ArtistRepository;
import com.harryseong.microservices.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class ApiControllerV1 {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private SongRepository songRepository;

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping( "/greeting")
    public Greeting getGreeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }

    @GetMapping("/artist")
    public Artist getArtist(@RequestParam(value="id", defaultValue="1") long id) { return artistRepository.findById(id); }

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
