package com.example.mangalfera.service;

import com.example.mangalfera.Mapper.PartnerPreferenceMapper;
import com.example.mangalfera.Mapper.ProfileMapper;
import com.example.mangalfera.dto.PartnerPreferenceDTO;
import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.model.PartnerPreference;
import com.example.mangalfera.model.Profile;
import com.example.mangalfera.model.User;
import com.example.mangalfera.repository.PartnerPreferenceRepository;
import com.example.mangalfera.repository.UserRepository;
import com.example.mangalfera.security.LoggedInUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PartnerPreferenceServiceImpl implements PartnerPreferenceService {
    @Autowired
    PartnerPreferenceRepository partnerPreferenceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public PartnerPreferenceDTO createPreference(PartnerPreferenceDTO partnerPreferenceDTO) {
        PartnerPreference partnerPreference = PartnerPreferenceMapper.toEntity(partnerPreferenceDTO);
        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setId(user.getId());
        partnerPreference.setUser(user);
        partnerPreference.setCreatedBy(LoggedInUserUtil.getLoggedInEmail());
        partnerPreference.setCreatedDate(new Date());
        PartnerPreference savePreference = partnerPreferenceRepository.save(partnerPreference);
        return PartnerPreferenceMapper.toDTO(savePreference);
    }

    @Override
    public PartnerPreferenceDTO updatePreference(Long id, PartnerPreferenceDTO partnerPreferenceDTO) {
        Optional<PartnerPreference> existing = partnerPreferenceRepository.findById(id);
        if (existing.isPresent()) {
            PartnerPreference updatedData  = PartnerPreferenceMapper.toEntity(partnerPreferenceDTO);
            updatedData.setId(id);
            Long userId = partnerPreferenceDTO.getUserId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            updatedData.setUser(user);
            updatedData.setUpdatedBy(LoggedInUserUtil.getLoggedInEmail());
            updatedData.setUpdatedData(new Date());
            PartnerPreference updatedPreference = partnerPreferenceRepository.save(updatedData);
            return PartnerPreferenceMapper.toDTO(updatedPreference);
        } else {
            throw new RuntimeException("PartnerPreference not found with id: " + id);
        }
    }

    @Override
    public PartnerPreferenceDTO getPartnerPreferenceById(Long  id) {
        PartnerPreference partnerPreference = partnerPreferenceRepository.findById(id).orElseThrow(() -> new RuntimeException("partnerPreference not found"));
        return PartnerPreferenceMapper.toDTO(partnerPreference);
    }

    @Override
    public PartnerPreferenceDTO getUserById(Long id) {
        PartnerPreference partnerPreferenceData = partnerPreferenceRepository.findByUserId(id).orElseThrow();
        return PartnerPreferenceMapper.toDTO(partnerPreferenceData);
    }

}
