package com.harryseong.microservices.repository;

import com.harryseong.microservices.domain.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    Song findById(long id);
    List<Song> findAll();
}
