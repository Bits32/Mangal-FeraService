package com.example.mangalfera.repository;

import com.example.mangalfera.model.Shortlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortlistRepository extends JpaRepository<Shortlist, Long> {
    List<Shortlist> findByUserId(Long userId);

    boolean existsByUserIdAndProfileId(Long userId, Long profileId);

    void deleteByUserIdAndProfileId(Long userId, Long profileId);
}
