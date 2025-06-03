package com.example.mangalfera.service;

import com.example.mangalfera.dto.PartnerPreferenceDTO;
import com.example.mangalfera.dto.ProfileDTO;

public interface PartnerPreferenceService {

    PartnerPreferenceDTO createPreference(PartnerPreferenceDTO partnerPreferenceDTO);
    PartnerPreferenceDTO updatePreference(Long id, PartnerPreferenceDTO partnerPreferenceDTO);
    PartnerPreferenceDTO getPartnerPreferenceById(Long id);
    PartnerPreferenceDTO getUserById(Long id);

}
