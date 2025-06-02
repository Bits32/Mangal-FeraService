package com.example.mangalfera.Mapper;

import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.model.Profile;

public class ProfileMapper {

    public static ProfileDTO toDTO(Profile profile) {
        ProfileDTO dto = new ProfileDTO();

        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setFirstName(profile.getFirstName());
        dto.setMiddleName(profile.getMiddleName());
        dto.setLastName(profile.getLastName());
        dto.setGender(profile.getGender());
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setHeightInCm(profile.getHeightInCm());
        dto.setWeightInKg(profile.getWeightInKg());
        dto.setBloodGroup(profile.getBloodGroup());
        dto.setMaritalStatus(profile.getMaritalStatus());

        dto.setReligion(profile.getReligion());
        dto.setCaste(profile.getCaste());
        dto.setSubCaste(profile.getSubCaste());
        dto.setMotherTongue(profile.getMotherTongue());

        dto.setEducation(profile.getEducation());
        dto.setOccupation(profile.getOccupation());
        dto.setCompany(profile.getCompany());
        dto.setAnnualIncome(profile.getAnnualIncome());

        dto.setCountry(profile.getCountry());
        dto.setState(profile.getState());
        dto.setCity(profile.getCity());
        dto.setAddress(profile.getAddress());

        dto.setComplexion(profile.getComplexion());
        dto.setBodyType(profile.getBodyType());

        dto.setPreferredContactTime(profile.getPreferredContactTime());
        dto.setMobileNo(profile.getMobileNo());
        dto.setWhatsappNo(profile.getWhatsappNo());
        dto.setEmail(profile.getEmail());

        dto.setRashi(profile.getRashi());
        dto.setNakshatra(profile.getNakshatra());
        dto.setGotra(profile.getGotra());
        dto.setManglik(profile.getManglik());

        dto.setProfilePhotoUrl(profile.getProfilePhotoUrl());
        dto.setProfileVideoUrl(profile.getProfileVideoUrl());
        dto.setAboutYourself(profile.getAboutYourself());

        dto.setPermissionImage(profile.getPermissionImage());
        dto.setPermissionVideo(profile.getPermissionVideo());

        dto.setCreatedBy(profile.getCreatedBy());
        dto.setCreatedDate(profile.getCreatedDate());
        dto.setUpdatedBy(profile.getUpdatedBy());
        dto.setUpdatedData(profile.getUpdatedData());

        return dto;
    }

    public static Profile toEntity(ProfileDTO dto) {
        Profile profile = new Profile();

        profile.setId(dto.getId());
        // profile.setUser(...) -> Set in service after fetching User entity by ID
        profile.setFirstName(dto.getFirstName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setHeightInCm(dto.getHeightInCm());
        profile.setWeightInKg(dto.getWeightInKg());
        profile.setBloodGroup(dto.getBloodGroup());
        profile.setMaritalStatus(dto.getMaritalStatus());

        profile.setReligion(dto.getReligion());
        profile.setCaste(dto.getCaste());
        profile.setSubCaste(dto.getSubCaste());
        profile.setMotherTongue(dto.getMotherTongue());

        profile.setEducation(dto.getEducation());
        profile.setOccupation(dto.getOccupation());
        profile.setCompany(dto.getCompany());
        profile.setAnnualIncome(dto.getAnnualIncome());

        profile.setCountry(dto.getCountry());
        profile.setState(dto.getState());
        profile.setCity(dto.getCity());
        profile.setAddress(dto.getAddress());

        profile.setComplexion(dto.getComplexion());
        profile.setBodyType(dto.getBodyType());

        profile.setPreferredContactTime(dto.getPreferredContactTime());
        profile.setMobileNo(dto.getMobileNo());
        profile.setWhatsappNo(dto.getWhatsappNo());
        profile.setEmail(dto.getEmail());

        profile.setRashi(dto.getRashi());
        profile.setNakshatra(dto.getNakshatra());
        profile.setGotra(dto.getGotra());
        profile.setManglik(dto.getManglik());

        profile.setProfilePhotoUrl(dto.getProfilePhotoUrl());
        profile.setProfileVideoUrl(dto.getProfileVideoUrl());
        profile.setAboutYourself(dto.getAboutYourself());

        profile.setPermissionImage(dto.getPermissionImage());
        profile.setPermissionVideo(dto.getPermissionVideo());

        profile.setCreatedBy(dto.getCreatedBy());
        profile.setCreatedDate(dto.getCreatedDate());
        profile.setUpdatedBy(dto.getUpdatedBy());
        profile.setUpdatedData(dto.getUpdatedData());

        return profile;
    }
}
