package com.example.mangalfera.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Shortlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;

    private LocalDateTime shortlistedAt;
    private String shortlistedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public LocalDateTime getShortlistedAt() {
        return shortlistedAt;
    }

    public void setShortlistedAt(LocalDateTime shortlistedAt) {
        this.shortlistedAt = shortlistedAt;
    }

    public String getShortlistedBy() {
        return shortlistedBy;
    }

    public void setShortlistedBy(String shortlistedBy) {
        this.shortlistedBy = shortlistedBy;
    }
}
