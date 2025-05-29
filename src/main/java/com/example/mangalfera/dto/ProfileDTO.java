package com.example.mangalfera.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ProfileDTO {
    private Long id;
    private Long userId;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private Integer heightInCm;
    private Integer weightInKg;
    private String bloodGroup;
    private String maritalStatus;

    private String religion;
    private String caste;
    private String subCaste;
    private String motherTongue;

    private String education;
    private String occupation;
    private String company;
    private Integer annualIncome;

    private String country;
    private String state;
    private String city;
    private String address;

    private String complexion;
    private String bodyType;

    private String preferredContactTime;

    private String rashi;
    private String nakshatra;
    private String gotra;
    private String manglik;

    private String profilePhotoUrl;
    private String profileVideoUrl;
    private String aboutYourself;

    private Boolean permissionImage;
    private Boolean permissionVideo;

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedData;
}
