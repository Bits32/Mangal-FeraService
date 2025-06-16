package com.example.mangalfera.dto;

import lombok.Data;

@Data
public class ShortlistRequest {
    private Long userId;
    private Long profileId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
