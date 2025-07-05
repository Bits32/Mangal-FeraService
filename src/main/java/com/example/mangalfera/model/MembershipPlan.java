package com.example.mangalfera.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "membership_plans")
public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String duration;
    private double originalPrice;
    private int discountPercent;
    private double discountedPrice;
    private double pricePerMonth;
    private String highlight;
    private Integer contactViewLimit;
    private Integer photosAllowedLimit;
    private Integer videoAllowedLimit;

    @ElementCollection
    @CollectionTable(name = "membership_plan_features", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "feature")
    private List<String> features;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public Integer getContactViewLimit() {
        return contactViewLimit;
    }

    public void setContactViewLimit(Integer contactViewLimit) {
        this.contactViewLimit = contactViewLimit;
    }

    public Integer getPhotosAllowedLimit() {
        return photosAllowedLimit;
    }

    public void setPhotosAllowedLimit(Integer photosAllowedLimit) {
        this.photosAllowedLimit = photosAllowedLimit;
    }

    public Integer getVideoAllowedLimit() {
        return videoAllowedLimit;
    }

    public void setVideoAllowedLimit(Integer videoAllowedLimit) {
        this.videoAllowedLimit = videoAllowedLimit;
    }
}
