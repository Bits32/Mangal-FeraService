package com.example.mangalfera.repository;


import com.example.mangalfera.model.PartnerPreference;
import com.example.mangalfera.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PartnerPreferenceRepository extends JpaRepository<PartnerPreference, Long> {
    Optional<PartnerPreference> findByUserId(Long userId);
}
