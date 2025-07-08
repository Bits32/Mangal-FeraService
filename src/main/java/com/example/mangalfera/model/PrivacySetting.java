package com.example.mangalfera.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PrivacySetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false, unique = true)
    private Long profileId;

    private boolean photoPublic;
    private boolean videoPublic;
    private boolean familyDetailsPublic;
    private boolean horoscopePublic;
    private boolean locationPublic;

    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public boolean isPhotoPublic() {
        return photoPublic;
    }

    public void setPhotoPublic(boolean photoPublic) {
        this.photoPublic = photoPublic;
    }

    public boolean isVideoPublic() {
        return videoPublic;
    }

    public void setVideoPublic(boolean videoPublic) {
        this.videoPublic = videoPublic;
    }

    public boolean isFamilyDetailsPublic() {
        return familyDetailsPublic;
    }

    public void setFamilyDetailsPublic(boolean familyDetailsPublic) {
        this.familyDetailsPublic = familyDetailsPublic;
    }

    public boolean isHoroscopePublic() {
        return horoscopePublic;
    }

    public void setHoroscopePublic(boolean horoscopePublic) {
        this.horoscopePublic = horoscopePublic;
    }

    public boolean isLocationPublic() {
        return locationPublic;
    }

    public void setLocationPublic(boolean locationPublic) {
        this.locationPublic = locationPublic;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
