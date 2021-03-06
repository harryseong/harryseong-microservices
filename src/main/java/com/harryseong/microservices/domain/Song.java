package com.harryseong.microservices.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "artist_song", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore
    private Set<Artist> artists;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    public Song() {
    }

    public Song(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
