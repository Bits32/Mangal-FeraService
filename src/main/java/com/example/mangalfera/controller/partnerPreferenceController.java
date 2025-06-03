package com.example.mangalfera.controller;

import com.example.mangalfera.dto.PartnerPreferenceDTO;
import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.service.PartnerPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partnerPreference")
public class partnerPreferenceController {

    @Autowired
    private PartnerPreferenceService partnerPreferenceService;

    @PostMapping
    public ResponseEntity<PartnerPreferenceDTO> createPreference(@RequestBody PartnerPreferenceDTO partnerPreferenceDTO){
        PartnerPreferenceDTO createPreference = partnerPreferenceService.createPreference(partnerPreferenceDTO);
        return ResponseEntity.ok(createPreference);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartnerPreferenceDTO> updatePreference(@PathVariable Long id, @RequestBody PartnerPreferenceDTO partnerPreferenceDTO) {
        PartnerPreferenceDTO updatePreference = partnerPreferenceService.updatePreference(id, partnerPreferenceDTO);
        return ResponseEntity.ok(updatePreference);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerPreferenceDTO> getPartnerPreferenceById(@PathVariable Long id) {
        PartnerPreferenceDTO partnerPreferenceDTO = partnerPreferenceService.getPartnerPreferenceById(id);
        return ResponseEntity.ok(partnerPreferenceDTO);
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<PartnerPreferenceDTO> getUserById(@PathVariable Long id) {
        PartnerPreferenceDTO PartnerPreferenceData = partnerPreferenceService.getUserById(id);
        return ResponseEntity.ok(PartnerPreferenceData);
    }
}
