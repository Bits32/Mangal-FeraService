package com.example.mangalfera.service;

import com.example.mangalfera.Mapper.ProfileMapper;
import com.example.mangalfera.dto.PartnerPreferenceDTO;
import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.model.Profile;
import com.example.mangalfera.model.User;
import com.example.mangalfera.repository.ProfileRepository;
import com.example.mangalfera.repository.UserRepository;
import com.example.mangalfera.security.LoggedInUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService  {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PartnerPreferenceService partnerPreferenceService;

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile profile = ProfileMapper.toEntity(profileDTO);
        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setId(user.getId());
        profile.setUser(user);
        profile.setCreatedBy(LoggedInUserUtil.getLoggedInEmail());
        profile.setCreatedDate(new Date());
        Profile savedProfile = profileRepository.save(profile);
        return ProfileMapper.toDTO(savedProfile);
    }

    @Override
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Optional<Profile> existing = profileRepository.findById(id);
        if (existing.isPresent()) {
            Profile updatedData  = ProfileMapper.toEntity(profileDTO);
            updatedData.setId(id);
            Long userId = profileDTO.getUserId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            updatedData.setUser(user);
            updatedData.setUpdatedBy(LoggedInUserUtil.getLoggedInEmail());
            updatedData.setUpdatedData(new Date());
            Profile updatedProfile = profileRepository.save(updatedData);
            return ProfileMapper.toDTO(updatedProfile);
        } else {
            throw new RuntimeException("Profile not found with id: " + id);
        }
    }

    @Override
    public ProfileDTO getProfileById(Long  id) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        return ProfileMapper.toDTO(profile);
    }

    @Override
    public ProfileDTO getUserById(Long id) {
        Profile profile = profileRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        return ProfileMapper.toDTO(profile);
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(ProfileMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfileDTO> getMatchedProfiles() {
        // 1. Get logged-in user's email
        String email = LoggedInUserUtil.getLoggedInEmail();

        // 2. Get user
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // 3. Get user's partner preference
        PartnerPreferenceDTO preferenceDTO = partnerPreferenceService.getUserById(user.getId());
        if (preferenceDTO == null) {
            throw new RuntimeException("Partner preference not found for user: " + user.getId());
        }

        // 4. Filter profiles (basic logic, you can refine it)
        List<Profile> allProfiles = profileRepository.findAll();
        List<Profile> matchedProfiles = allProfiles.stream()
                .filter(p -> {
                    if (p.getUser().getId().equals(user.getId())) return false; // skip self

                    boolean ageMatch = true;
                    boolean heightMatch = true;
                    boolean maritalMatch = true;
                    boolean religionMatch = true;
                    boolean casteMatch = true;
                    boolean locationMatch = true;
                    boolean manglikMatch = true;

                    // Age check
                    if (preferenceDTO.getAgeFrom() != null && preferenceDTO.getAgeFrom() != null && p.getDateOfBirth() != null) {
                        int age = LocalDate.now().getYear() - p.getDateOfBirth().getYear();
                        ageMatch = age >= preferenceDTO.getAgeFrom() && age <= preferenceDTO.getAgeTo();
                    }

                    // Height check
                    if (preferenceDTO.getHeightFrom() != null && preferenceDTO.getHeightTo() != null && p.getHeightInCm() != null) {
                        try {
                            int heightFrom = Integer.parseInt(preferenceDTO.getHeightFrom());
                            int heightTo = Integer.parseInt(preferenceDTO.getHeightTo());
                            heightMatch = p.getHeightInCm() >= heightFrom && p.getHeightInCm() <= heightTo;
                        } catch (NumberFormatException e) {
                            heightMatch = false;
                        }
                    }

                    // Marital Status
                    if (preferenceDTO.getMaritalStatus() != null) {
                        maritalMatch = preferenceDTO.getMaritalStatus().equalsIgnoreCase(p.getMaritalStatus());
                    }

                    // Religion / Caste / Location
                    if (preferenceDTO.getReligion() != null) {
                        religionMatch = preferenceDTO.getReligion().equalsIgnoreCase(p.getReligion());
                    }

                    if (preferenceDTO.getCaste() != null) {
                        casteMatch = preferenceDTO.getCaste().equalsIgnoreCase(p.getCaste());
                    }

                    if (preferenceDTO.getCountry() != null) {
                        locationMatch = preferenceDTO.getCountry().equalsIgnoreCase(p.getCountry());
                    }

                    // Manglik
                    if (preferenceDTO.getManglik() != null) {
                        manglikMatch = preferenceDTO.getManglik().equalsIgnoreCase(p.getManglik());
                    }

                    return ageMatch && heightMatch && maritalMatch && religionMatch &&
                            casteMatch && locationMatch && manglikMatch;
                })
                .collect(Collectors.toList());

        return matchedProfiles.stream()
                .map(ProfileMapper::toDTO)
                .collect(Collectors.toList());
    }


}
