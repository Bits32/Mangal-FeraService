package com.example.mangalfera.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class RegisterRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private String contactNumber;
    private String email;
    private String profileCreatedFor;
    private String membershipPlanId;
    private String membershipPlan;
    private Date membershipStartPlan;
    private Date membershipEndPlan;
    private Integer contactViewCount;
    private Integer contactViewLimit;
    private Integer videosLimit;
    private Integer photosLimits;
    private Integer videosUseCounts;
    private Integer photosUseCounts;
    private String viewedProfileIds;
    private Integer remainingContactViews;
    private Integer remainingPhotos;
    private Integer remainingVideos;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileCreatedFor() {
        return profileCreatedFor;
    }

    public void setProfileCreatedFor(String profileCreatedFor) {
        this.profileCreatedFor = profileCreatedFor;
    }

    public String getMembershipPlanId() {
        return membershipPlanId;
    }

    public void setMembershipPlanId(String membershipPlanId) {
        this.membershipPlanId = membershipPlanId;
    }

    public String getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(String membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    public Date getMembershipStartPlan() {
        return membershipStartPlan;
    }

    public void setMembershipStartPlan(Date membershipStartPlan) {
        this.membershipStartPlan = membershipStartPlan;
    }

    public Date getMembershipEndPlan() {
        return membershipEndPlan;
    }

    public void setMembershipEndPlan(Date membershipEndPlan) {
        this.membershipEndPlan = membershipEndPlan;
    }

    public Integer getContactViewCount() {
        return contactViewCount;
    }

    public void setContactViewCount(Integer contactViewCount) {
        this.contactViewCount = contactViewCount;
    }

    public Integer getVideosLimit() {
        return videosLimit;
    }

    public void setVideosLimit(Integer videosLimit) {
        this.videosLimit = videosLimit;
    }

    public Integer getPhotosLimits() {
        return photosLimits;
    }

    public void setPhotosLimits(Integer photosLimits) {
        this.photosLimits = photosLimits;
    }

    public Integer getContactViewLimit() {
        return contactViewLimit;
    }

    public void setContactViewLimit(Integer contactViewLimit) {
        this.contactViewLimit = contactViewLimit;
    }

    public Integer getVideosUseCounts() {
        return videosUseCounts;
    }

    public void setVideosUseCounts(Integer videosUseCounts) {
        this.videosUseCounts = videosUseCounts;
    }

    public Integer getPhotosUseCounts() {
        return photosUseCounts;
    }

    public void setPhotosUseCounts(Integer photosUseCounts) {
        this.photosUseCounts = photosUseCounts;
    }

    public String getViewedProfileIds() {
        return viewedProfileIds;
    }

    public void setViewedProfileIds(String viewedProfileIds) {
        this.viewedProfileIds = viewedProfileIds;
    }

    public Integer getRemainingContactViews() {
        return remainingContactViews;
    }

    public void setRemainingContactViews(Integer remainingContactViews) {
        this.remainingContactViews = remainingContactViews;
    }

    public Integer getRemainingPhotos() {
        return remainingPhotos;
    }

    public void setRemainingPhotos(Integer remainingPhotos) {
        this.remainingPhotos = remainingPhotos;
    }

    public Integer getRemainingVideos() {
        return remainingVideos;
    }

    public void setRemainingVideos(Integer remainingVideos) {
        this.remainingVideos = remainingVideos;
    }
}
