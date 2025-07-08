package com.example.mangalfera.service;

import com.example.mangalfera.model.ProfileViewNotification;
import com.example.mangalfera.repository.ProfileViewNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileViewNotificationService {
    private final ProfileViewNotificationRepository repository;

    public ProfileViewNotificationService(ProfileViewNotificationRepository repository) {
        this.repository = repository;
    }

    public void saveProfileView(Long viewerId, Long viewedId) {
        if (!viewerId.equals(viewedId)) {
            ProfileViewNotification notification = new ProfileViewNotification(viewerId, viewedId);
            repository.save(notification);
        }
    }

    public List<ProfileViewNotification> getUnreadNotifications(Long profileId) {
        return repository.findByViewedProfileIdAndIsReadFalse(profileId);
    }

    public List<ProfileViewNotification> getAllNotifications(Long profileId) {
        return repository.findByViewedProfileId(profileId); // ✅ correct method
    }

    public void markAsRead(Long notificationId) {
        repository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            repository.save(notification);
        });
    }
}
