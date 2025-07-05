package com.example.mangalfera.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String profileId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Integer heightInCm;
    private Integer weightInKg;
    private String bloodGroup;
    private String maritalStatus;
    private String grewUp;
    private String diet;
    private String healthInformation;
    private String healthOtherDetails;
    private String disability;
    private String presentlyStyingState;
    private String presentlyStyingCity;

    // Religion & Caste
    private String religion;
    private String caste;
    private String subCaste;
    private String religionPath;
    private String motherTongue;
    private String community;
    private String subCommunity;


    // Education & Profession
    private String ethnicOrigin;
    private String education;
    private String collegeName;
    private String workingWith;
    private String workingAs;
    private String occupation;
    private String occupationPosition;
    private String company;
    private String annualIncome;
    private String distanceStudding;


    // Location
    private String country;
    private String state;
    private String city;
    private String address;
    private String pinCode;
    private String residencyStatus;

    // Appearance
    private String complexion;
    private String bodyType;

    // Contact Preferences
    private String preferredContactTime;
    private String mobileNo;
    private String whatsappNo;
    private String email;

    // Horoscope (Optional)
    private String rashi;
    private String nakshatra;
    private String gotra;
    private String manglik;
    private String countryOfBirth;
    private String stateOfBirth;
    private String cityOfBirth;
    private String placeOFBirth;
    private String timeOfBirth;

    // Family details
    private String motherName;
    private String motherDetails;
    private String fatherDetails;
    private String motherHomeTown;
    private String fatherHomeTown;
    private String familyLocation;

    @ElementCollection
    @CollectionTable(name = "profile_sisters", joinColumns = @JoinColumn(name = "profile_id"))
    private List<Sibling> sisters;

    @ElementCollection
    @CollectionTable(name = "profile_brothers", joinColumns = @JoinColumn(name = "profile_id"))
    private List<Sibling> brothers;

    private String familyFinancialStatus;
    private String fatherEducation;
    private String motherEducation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private List<Child> children = new ArrayList<>();

    @Column(name = "hobbies")
    private String hobbiesRaw;

    @Transient
    private List<String> hobbies;

    // Media
    private String profilePhotoUrl;
    private String profileVideoUrl;

    @Column(length = 2000)
    private String aboutYourself;

    // Metadata
    private Boolean active = true;

    private Boolean permissionImage;
    private Boolean permissionVideo;

    private String memberType;

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedData;
}
