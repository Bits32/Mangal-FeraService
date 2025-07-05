package com.example.mangalfera.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PartnerPreference")
public class PartnerPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
