package com.example.mangalfera.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PartnerPreferenceDTO {
    private Long id;
    private Long userId;
    public Integer ageFrom;
    public Integer ageTo;
    public String heightFrom;
    public String heightTo;
    public String maritalStatus;
    public String religion;
    public String caste;
    public String motherTongue;
    public String education;
    public String occupation;
    public String incomeFrom;
    public String incomeTo;
    public String country;
    public String state;
    public String city;
    public String manglik;
    public String diet;

    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedData;
}
