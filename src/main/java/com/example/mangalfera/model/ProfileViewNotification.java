package com.example.mangalfera.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "profile_view_notifications")
public class ProfileViewNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long viewerProfileId;

    private Long viewedProfileId;

    private Date viewedAt;

    private boolean isRead = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viewerProfileId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Profile profile;

    public ProfileViewNotification() {
    }

    public ProfileViewNotification(Long viewerProfileId, Long viewedProfileId) {
        this.viewerProfileId = viewerProfileId;
        this.viewedProfileId = viewedProfileId;
        this.viewedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViewerProfileId() {
        return viewerProfileId;
    }

    public void setViewerProfileId(Long viewerProfileId) {
        this.viewerProfileId = viewerProfileId;
    }

    public Long getViewedProfileId() {
        return viewedProfileId;
    }

    public void setViewedProfileId(Long viewedProfileId) {
        this.viewedProfileId = viewedProfileId;
    }

    public Date getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(Date viewedAt) {
        this.viewedAt = viewedAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
