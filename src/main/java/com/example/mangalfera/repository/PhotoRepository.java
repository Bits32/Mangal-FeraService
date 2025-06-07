package com.example.mangalfera.repository;

import com.example.mangalfera.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
        List<Photo> findByProfileId(Long profileId);
}
