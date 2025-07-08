package com.example.mangalfera.repository;

import com.example.mangalfera.model.ProfileViewNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileViewNotificationRepository extends JpaRepository<ProfileViewNotification, Long> {
    List<ProfileViewNotification> findByViewedProfileIdAndIsReadFalse(Long viewedProfileId);
    List<ProfileViewNotification> findByViewedProfileId(Long viewedProfileId);
}