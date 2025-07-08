package com.example.mangalfera.dto;

public class PrivacySettingDto {
    private Long profileId;
    private Boolean photoPublic;
    private Boolean videoPublic;
    private Boolean familyDetailsPublic;
    private Boolean horoscopePublic;
    private Boolean locationPublic;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Boolean getPhotoPublic() {
        return photoPublic;
    }

    public void setPhotoPublic(Boolean photoPublic) {
        this.photoPublic = photoPublic;
    }

    public Boolean getVideoPublic() {
        return videoPublic;
    }

    public void setVideoPublic(Boolean videoPublic) {
        this.videoPublic = videoPublic;
    }

    public Boolean getFamilyDetailsPublic() {
        return familyDetailsPublic;
    }

    public void setFamilyDetailsPublic(Boolean familyDetailsPublic) {
        this.familyDetailsPublic = familyDetailsPublic;
    }

    public Boolean getHoroscopePublic() {
        return horoscopePublic;
    }

    public void setHoroscopePublic(Boolean horoscopePublic) {
        this.horoscopePublic = horoscopePublic;
    }

    public Boolean getLocationPublic() {
        return locationPublic;
    }

    public void setLocationPublic(Boolean locationPublic) {
        this.locationPublic = locationPublic;
    }
}
