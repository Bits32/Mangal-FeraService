package com.example.mangalfera.controller;

import com.example.mangalfera.model.ProfileViewNotification;
import com.example.mangalfera.service.ProfileViewNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile-views")
public class ProfileViewNotificationController {
    private final ProfileViewNotificationService service;

    public ProfileViewNotificationController(ProfileViewNotificationService service) {
        this.service = service;
    }

    @PostMapping("/view")
    public ResponseEntity<String> logProfileView(@RequestParam Long viewerId, @RequestParam Long viewedId) {
        service.saveProfileView(viewerId, viewedId);
        return ResponseEntity.ok("View logged");
    }

    @GetMapping("/unread/{profileId}")
    public ResponseEntity<List<ProfileViewNotification>> getUnreadNotifications(@PathVariable Long profileId) {
        return ResponseEntity.ok(service.getUnreadNotifications(profileId));
    }

    @GetMapping("/all/{profileId}")
    public ResponseEntity<List<ProfileViewNotification>> getAllNotifications(@PathVariable Long profileId) {
        return ResponseEntity.ok(service.getAllNotifications(profileId));
    }


    @PostMapping("/mark-read/{notificationId}")
    public ResponseEntity<String> markNotificationRead(@PathVariable Long notificationId) {
        service.markAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read");
    }
}
