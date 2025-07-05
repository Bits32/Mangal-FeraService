package com.example.mangalfera.repository;

import com.example.mangalfera.model.PrivacySetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivacySettingRepository extends JpaRepository<PrivacySetting, Long> {
    Optional<PrivacySetting> findByProfileId(Long profileId);
}

