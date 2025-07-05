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

    private boolean isPhotoPublic;
    private boolean isVideoPublic;
    private boolean isFamilyDetailsPublic;
    private boolean isHoroscopePublic;
    private boolean isLocationPublic;

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
        return isPhotoPublic;
    }

    public void setPhotoPublic(boolean photoPublic) {
        isPhotoPublic = photoPublic;
    }

    public boolean isVideoPublic() {
        return isVideoPublic;
    }

    public void setVideoPublic(boolean videoPublic) {
        isVideoPublic = videoPublic;
    }

    public boolean isFamilyDetailsPublic() {
        return isFamilyDetailsPublic;
    }

    public void setFamilyDetailsPublic(boolean familyDetailsPublic) {
        isFamilyDetailsPublic = familyDetailsPublic;
    }

    public boolean isHoroscopePublic() {
        return isHoroscopePublic;
    }

    public void setHoroscopePublic(boolean horoscopePublic) {
        isHoroscopePublic = horoscopePublic;
    }

    public boolean isLocationPublic() {
        return isLocationPublic;
    }

    public void setLocationPublic(boolean locationPublic) {
        isLocationPublic = locationPublic;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
