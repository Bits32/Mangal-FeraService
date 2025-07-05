package com.example.mangalfera.Mapper;

import com.example.mangalfera.dto.PartnerPreferenceDTO;
import com.example.mangalfera.model.PartnerPreference;

public class PartnerPreferenceMapper {
    public static PartnerPreferenceDTO toDTO(PartnerPreference preference) {
        PartnerPreferenceDTO dto = new PartnerPreferenceDTO();
        dto.setId(preference.getId());
        dto.setUserId(preference.getUser().getId());
        dto.setAgeFrom(preference.getAgeFrom());
        dto.setAgeTo(preference.getAgeTo());
        dto.setHeightFrom(preference.getHeightFrom());
        dto.setHeightTo(preference.getHeightTo());
        dto.setMaritalStatus(preference.getMaritalStatus());
        dto.setReligion(preference.getReligion());
        dto.setCaste(preference.getCaste());
        dto.setMotherTongue(preference.getMotherTongue());
        dto.setEducation(preference.getEducation());
        dto.setOccupation(preference.getOccupation());
        dto.setIncomeFrom(preference.getIncomeFrom());
        dto.setIncomeTo(preference.getIncomeTo());
        dto.setCountry(preference.getCountry());
        dto.setState(preference.getState());
        dto.setCity(preference.getCity());
        dto.setManglik(preference.getManglik());
        dto.setDiet(preference.getDiet());

        dto.setCreatedBy(preference.getCreatedBy());
        dto.setCreatedDate(preference.getCreatedDate());
        dto.setUpdatedBy(preference.getUpdatedBy());
        dto.setUpdatedData(preference.getUpdatedData());
        return dto;
    }

    public static PartnerPreference toEntity(PartnerPreferenceDTO dto) {
        PartnerPreference preference = new PartnerPreference();
        preference.setId(dto.getId());
//        entity.setUser(user);  // IMPORTANT: User object should be fetched by ID
        preference.setAgeFrom(dto.getAgeFrom());
        preference.setAgeTo(dto.getAgeTo());
        preference.setHeightFrom(dto.getHeightFrom());
        preference.setHeightTo(dto.getHeightTo());
        preference.setMaritalStatus(dto.getMaritalStatus());
        preference.setReligion(dto.getReligion());
        preference.setCaste(dto.getCaste());
        preference.setMotherTongue(dto.getMotherTongue());
        preference.setEducation(dto.getEducation());
        preference.setOccupation(dto.getOccupation());
        preference.setIncomeFrom(dto.getIncomeFrom());
        preference.setIncomeTo(dto.getIncomeTo());
        preference.setCountry(dto.getCountry());
        preference.setState(dto.getState());
        preference.setCity(dto.getCity());
        preference.setManglik(dto.getManglik());
        preference.setDiet(dto.getDiet());

        preference.setCreatedBy(dto.getCreatedBy());
        preference.setCreatedDate(dto.getCreatedDate());
        preference.setUpdatedBy(dto.getUpdatedBy());
        preference.setUpdatedData(dto.getUpdatedData());
        return preference;
    }
}
