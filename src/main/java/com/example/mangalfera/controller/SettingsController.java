package com.example.mangalfera.controller;

import com.example.mangalfera.dto.PrivacySettingDto;
import com.example.mangalfera.model.PrivacySetting;
import com.example.mangalfera.repository.PrivacySettingRepository;
import com.example.mangalfera.service.PrivacySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    PrivacySettings privacySettingService;

    @Autowired
    PrivacySettingRepository privacySettingRepository;

    @PostMapping("/privacy-settings")
    public ResponseEntity<?> updatePrivacy(@RequestBody PrivacySettingDto dto) {
        privacySettingService.updatePrivacySettings(dto);
        return ResponseEntity.ok("Privacy settings updated.");
    }

    @GetMapping("/privacy-settings/{profileId}")
    public ResponseEntity<PrivacySettingDto> getPrivacy(@PathVariable Long profileId) {
        Optional<PrivacySetting> optionalSetting = privacySettingRepository.findByProfileId(profileId);

        PrivacySettingDto dto = new PrivacySettingDto();
        dto.setProfileId(profileId);

        if (optionalSetting.isPresent()) {
            PrivacySetting setting = optionalSetting.get();
            dto.setPhotoPublic(setting.isPhotoPublic());
            dto.setVideoPublic(setting.isVideoPublic());
            dto.setFamilyDetailsPublic(setting.isFamilyDetailsPublic());
            dto.setHoroscopePublic(setting.isHoroscopePublic());
            dto.setLocationPublic(setting.isLocationPublic());
        } else {
            dto.setPhotoPublic(false);
            dto.setVideoPublic(false);
            dto.setFamilyDetailsPublic(false);
            dto.setHoroscopePublic(false);
            dto.setLocationPublic(false);
        }

        return ResponseEntity.ok(dto);
    }

}
