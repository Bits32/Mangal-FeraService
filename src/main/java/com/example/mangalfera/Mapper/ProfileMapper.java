package com.example.mangalfera.Mapper;

import com.example.mangalfera.dto.ChildDTO;
import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.dto.SubChildDTO;
import com.example.mangalfera.model.Child;
import com.example.mangalfera.model.Profile;
import com.example.mangalfera.model.SubChild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileMapper {

    public static ProfileDTO toDTO(Profile profile) {
        ProfileDTO dto = new ProfileDTO();

        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setProfileId(profile.getProfileId());
        dto.setFirstName(profile.getFirstName());
        dto.setMiddleName(profile.getMiddleName());
        dto.setLastName(profile.getLastName());
        dto.setGender(profile.getGender());
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setHeightInCm(profile.getHeightInCm());
        dto.setWeightInKg(profile.getWeightInKg());
        dto.setBloodGroup(profile.getBloodGroup());
        dto.setMaritalStatus(profile.getMaritalStatus());
        dto.setGrewUp(profile.getGrewUp());
        dto.setDiet(profile.getDiet());
        dto.setHealthInformation(profile.getHealthInformation());
        dto.setDisability(profile.getDisability());
        dto.setHealthOtherDetails(profile.getHealthOtherDetails());
        dto.setPresentlyStyingState(profile.getPresentlyStyingState());
        dto.setPresentlyStyingCity(profile.getPresentlyStyingCity());

        dto.setReligion(profile.getReligion());
        dto.setCaste(profile.getCaste());
        dto.setSubCaste(profile.getSubCaste());
        dto.setMotherTongue(profile.getMotherTongue());
        dto.setTimeOfBirth(profile.getTimeOfBirth());
        dto.setCountryOfBirth(profile.getCountryOfBirth());
        dto.setStateOfBirth(profile.getStateOfBirth());
        dto.setCityOfBirth(profile.getCityOfBirth());
        dto.setCommunity(profile.getCommunity());
        dto.setSubCommunity(profile.getSubCommunity());
        dto.setPlaceOFBirth(profile.getPlaceOFBirth());
        dto.setReligionPath(profile.getReligionPath());

        dto.setMotherName(profile.getMotherName());
        dto.setMotherDetails(profile.getMotherDetails());
        dto.setFatherDetails(profile.getFatherDetails());
        dto.setMotherHomeTown(profile.getMotherHomeTown());
        dto.setFamilyLocation(profile.getFamilyLocation());
        dto.setBrothers(profile.getBrothers() != null ? profile.getBrothers() : new ArrayList<>());
        dto.setSisters(profile.getSisters() != null ? profile.getSisters() : new ArrayList<>());
        dto.setFamilyFinancialStatus(profile.getFamilyFinancialStatus());
        dto.setFatherHomeTown(profile.getFatherHomeTown());
        dto.setFatherEducation(profile.getFatherEducation());
        dto.setMotherEducation(profile.getMotherEducation());
        dto.setChildren(
                profile.getChildren().stream().map(child -> {
                    ChildDTO childDTO = new ChildDTO();
                    childDTO.setName(child.getName());
                    childDTO.setAge(child.getAge());
                    childDTO.setEducation(child.getEducation());

                    List<SubChildDTO> subDTOs = child.getSubChildren().stream().map(sub -> {
                        SubChildDTO subDTO = new SubChildDTO();
                        subDTO.setName(sub.getName());
                        return subDTO;
                    }).collect(Collectors.toList());

                    childDTO.setSubChildren(subDTOs);
                    return childDTO;
                }).collect(Collectors.toList())
        );

        dto.setHobbiesRaw(profile.getHobbiesRaw());
        if (profile.getHobbiesRaw() != null && !profile.getHobbiesRaw().isEmpty()) {
            dto.setHobbies(Arrays.asList(profile.getHobbiesRaw().split(",")));
        } else {
            dto.setHobbies(new ArrayList<>());
        }

        dto.setEthnicOrigin(profile.getEthnicOrigin());
        dto.setEducation(profile.getEducation());
        dto.setCollegeName(profile.getCollegeName());
        dto.setWorkingWith(profile.getWorkingWith());
        dto.setWorkingAs(profile.getWorkingAs());
        dto.setOccupation(profile.getOccupation());
        dto.setCompany(profile.getCompany());
        dto.setAnnualIncome(profile.getAnnualIncome());
        dto.setDistanceStudding(profile.getDistanceStudding());
        dto.setOccupationPosition(profile.getOccupationPosition());

        dto.setCountry(profile.getCountry());
        dto.setState(profile.getState());
        dto.setCity(profile.getCity());
        dto.setPinCode(profile.getPinCode());
        dto.setAddress(profile.getAddress());
        dto.setResidencyStatus(profile.getResidencyStatus());

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

        dto.setMemberType(profile.getMemberType());

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
        profile.setProfileId(dto.getProfileId());
        profile.setFirstName(dto.getFirstName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setHeightInCm(dto.getHeightInCm());
        profile.setWeightInKg(dto.getWeightInKg());
        profile.setBloodGroup(dto.getBloodGroup());
        profile.setMaritalStatus(dto.getMaritalStatus());
        profile.setGrewUp(dto.getGrewUp());
        profile.setDiet(dto.getDiet());
        profile.setHealthInformation(dto.getHealthInformation());
        profile.setDisability(dto.getDisability());
        profile.setHealthOtherDetails(dto.getHealthOtherDetails());
        profile.setPresentlyStyingState(dto.getPresentlyStyingState());
        profile.setPresentlyStyingCity(dto.getPresentlyStyingCity());


        profile.setMotherName(dto.getMotherName());
        profile.setMotherDetails(dto.getMotherDetails());
        profile.setFatherDetails(dto.getFatherDetails());
        profile.setMotherHomeTown(dto.getMotherHomeTown());
        profile.setFamilyLocation(dto.getFamilyLocation());
        profile.setSisters(dto.getSisters() != null ? dto.getSisters() : new ArrayList<>());
        profile.setBrothers(dto.getBrothers() != null ? dto.getBrothers() : new ArrayList<>());
        profile.setFamilyFinancialStatus(dto.getFamilyFinancialStatus());
        profile.setFatherHomeTown(dto.getFatherHomeTown());
        profile.setFatherEducation(dto.getFatherEducation());
        profile.setMotherEducation(dto.getMotherEducation());
        profile.setChildren(
                dto.getChildren().stream().map(childDTO -> {
                    Child child = new Child();
                    child.setName(childDTO.getName());
                    child.setAge(childDTO.getAge());
                    child.setEducation(childDTO.getEducation());

                    List<SubChild> subs = childDTO.getSubChildren().stream().map(subDTO -> {
                        SubChild sub = new SubChild();
                        sub.setName(subDTO.getName());
                        return sub;
                    }).collect(Collectors.toList());

                    child.setSubChildren(subs);
                    return child;
                }).collect(Collectors.toList())
        );

        profile.setHobbiesRaw(dto.getHobbiesRaw());
        profile.setHobbies(dto.getHobbies());

        profile.setReligion(dto.getReligion());
        profile.setCaste(dto.getCaste());
        profile.setSubCaste(dto.getSubCaste());
        profile.setMotherTongue(dto.getMotherTongue());
        profile.setCountryOfBirth(dto.getCountryOfBirth());
        profile.setStateOfBirth(dto.getStateOfBirth());
        profile.setCityOfBirth(dto.getCityOfBirth());
        profile.setTimeOfBirth(dto.getTimeOfBirth());
        profile.setCommunity(dto.getCommunity());
        profile.setSubCommunity(dto.getSubCommunity());
        profile.setPlaceOFBirth(dto.getPlaceOFBirth());
        profile.setReligionPath(dto.getReligionPath());

        profile.setEthnicOrigin(dto.getEthnicOrigin());
        profile.setEducation(dto.getEducation());
        profile.setCollegeName(dto.getCollegeName());
        profile.setWorkingWith(dto.getWorkingWith());
        profile.setWorkingAs(dto.getWorkingAs());
        profile.setOccupation(dto.getOccupation());
        profile.setCompany(dto.getCompany());
        profile.setAnnualIncome(dto.getAnnualIncome());
        profile.setDistanceStudding(dto.getDistanceStudding());
        profile.setOccupationPosition(dto.getOccupationPosition());

        profile.setCountry(dto.getCountry());
        profile.setState(dto.getState());
        profile.setCity(dto.getCity());
        profile.setPinCode(dto.getPinCode());
        profile.setAddress(dto.getAddress());
        profile.setResidencyStatus(dto.getResidencyStatus());

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

        profile.setMemberType(dto.getMemberType());

        profile.setCreatedBy(dto.getCreatedBy());
        profile.setCreatedDate(dto.getCreatedDate());
        profile.setUpdatedBy(dto.getUpdatedBy());
        profile.setUpdatedData(dto.getUpdatedData());

        return profile;
    }
}
