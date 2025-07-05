package com.example.mangalfera.service;

import com.example.mangalfera.dto.PrivacySettingDto;
import com.example.mangalfera.model.PrivacySetting;
import com.example.mangalfera.repository.PrivacySettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PrivacySettings {

    @Autowired
    PrivacySettingRepository privacySettingRepository;

    public void updatePrivacySettings(PrivacySettingDto dto) {
        PrivacySetting setting = privacySettingRepository.findByProfileId(dto.getProfileId())
                .orElse(new PrivacySetting());

        setting.setProfileId(dto.getProfileId());
        setting.setPhotoPublic(dto.isPhotoPublic());
        setting.setVideoPublic(dto.isVideoPublic());
        setting.setFamilyDetailsPublic(dto.isFamilyDetailsPublic());
        setting.setHoroscopePublic(dto.isHoroscopePublic());
        setting.setLocationPublic(dto.isLocationPublic());
        setting.setUpdatedDate(new Date());

        privacySettingRepository.save(setting);
    }
}
