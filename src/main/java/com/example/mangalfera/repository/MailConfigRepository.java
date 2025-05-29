package com.example.mangalfera.repository;

import com.example.mangalfera.model.MailConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailConfigRepository extends JpaRepository<MailConfig, Long> {
    Optional<MailConfig> findByActiveTrue();

    @Modifying
    @Query("UPDATE MailConfig m SET m.active = false WHERE m.active = true")
    void deactivateAllConfigs();
}