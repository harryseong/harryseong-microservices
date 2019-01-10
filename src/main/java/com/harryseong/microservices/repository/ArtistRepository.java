package com.harryseong.microservices.repository;

import com.harryseong.microservices.domain.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Artist findById(long id);
    List<Artist> findAll();
}
