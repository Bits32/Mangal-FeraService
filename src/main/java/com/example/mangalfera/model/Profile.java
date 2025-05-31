package com.example.mangalfera.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Basic Details
    private String name;
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Integer heightInCm;
    private Integer weightInKg;
    private String bloodGroup;
    private String maritalStatus; // Single / Divorced / Widowed / etc.

    // Religion & Caste
    private String religion;
    private String caste;
    private String subCaste;
    private String motherTongue;

    // Education & Profession
    private String education;
    private String occupation;
    private String company;
    private Integer annualIncome;

    // Location
    private String country;
    private String state;
    private String city;
    private String address;

    // Appearance
    private String complexion;
    private String bodyType;

    // Contact Preferences
    private String preferredContactTime;

    // Horoscope (Optional)
    private String rashi;
    private String nakshatra;
    private String gotra;
    private String manglik; // Yes / No / Don't Know

    // Media
    private String profilePhotoUrl;
    private String profileVideoUrl;

    @Column(length = 2000)
    private String aboutYourself;

    // Metadata
    private Boolean active = true;

    private Boolean permissionImage;
    private Boolean permissionVideo;

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedData;
}
