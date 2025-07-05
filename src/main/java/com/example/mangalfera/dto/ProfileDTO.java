package com.example.mangalfera.dto;

import com.example.mangalfera.model.Sibling;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ProfileDTO {
    private Long id;
    private Long userId;
    private String profileId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
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

    private String religion;
    private String caste;
    private String subCaste;
    private String motherTongue;
    private String community;
    private String subCommunity;
    private String religionPath;

    private String ethnicOrigin;
    private String education;
    private String collegeName;
    private String workingWith;
    private String workingAs;
    private String occupation;
    private String company;
    private String annualIncome;
    private String occupationPosition;
    private String distanceStudding;

    private String country;
    private String state;
    private String city;
    private String address;
    private String pinCode;
    private String residencyStatus;

    private String complexion;
    private String bodyType;

    private String preferredContactTime;
    private String mobileNo;
    private String whatsappNo;
    private String email;

    private String rashi;
    private String nakshatra;
    private String gotra;
    private String manglik;
    private String timeOfBirth;
    private String countryOfBirth;
    private String stateOfBirth;
    private String cityOfBirth;
    private String placeOFBirth;

    private String motherName;
    private String motherDetails;
    private String fatherDetails;
    private String fatherHomeTown;
    private String motherHomeTown;
    private String familyLocation;
    private List<Sibling> sisters;
    private List<Sibling> brothers;
    private String familyFinancialStatus;
    private String fatherEducation;
    private String motherEducation;
    private List<ChildDTO> children;

    private String hobbiesRaw;
    private List<String> hobbies;

    private String profilePhotoUrl;
    private String profileVideoUrl;
    private String aboutYourself;

    private Boolean permissionImage;
    private Boolean permissionVideo;

    private String memberType;

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedData;
}
