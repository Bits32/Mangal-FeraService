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

//    @Autowired
//    private AmazonS3 amazonS3;

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
        String email = LoggedInUserUtil.getLoggedInEmail();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        PartnerPreferenceDTO preferenceDTO = partnerPreferenceService.getUserById(user.getId());
        if (preferenceDTO == null) {
            throw new RuntimeException("Partner preference not found for user: " + user.getId());
        }

        List<Profile> allProfiles = profileRepository.findAll();
        List<ProfileDTO> matchedProfiles = allProfiles.stream()
                .filter(p -> !p.getUser().getId().equals(user.getId()))
                .filter(p -> {
                    int total = 0;
                    int matched = 0;

                    if (preferenceDTO.getAgeFrom() != null && preferenceDTO.getAgeTo() != null && p.getDateOfBirth() != null) {
                        total++;
                        int age = LocalDate.now().getYear() - p.getDateOfBirth().getYear();
                        if (age >= preferenceDTO.getAgeFrom() && age <= preferenceDTO.getAgeTo()) matched++;
                    }

                    if (preferenceDTO.getHeightFrom() != null && preferenceDTO.getHeightTo() != null && p.getHeightInCm() != null) {
                        total++;
                        try {
                            int heightFrom = Integer.parseInt(preferenceDTO.getHeightFrom());
                            int heightTo = Integer.parseInt(preferenceDTO.getHeightTo());
                            if (p.getHeightInCm() >= heightFrom && p.getHeightInCm() <= heightTo) matched++;
                        } catch (NumberFormatException e) {}
                    }

                    if (preferenceDTO.getMaritalStatus() != null) {
                        total++;
                        if (preferenceDTO.getMaritalStatus().equalsIgnoreCase(p.getMaritalStatus())) matched++;
                    }

                    if (preferenceDTO.getReligion() != null) {
                        total++;
                        if (preferenceDTO.getReligion().equalsIgnoreCase(p.getReligion())) matched++;
                    }

                    if (preferenceDTO.getCaste() != null) {
                        total++;
                        if (preferenceDTO.getCaste().equalsIgnoreCase(p.getCaste())) matched++;
                    }

                    if (preferenceDTO.getCountry() != null) {
                        total++;
                        if (preferenceDTO.getCountry().equalsIgnoreCase(p.getCountry())) matched++;
                    }

                    if (preferenceDTO.getManglik() != null) {
                        total++;
                        if (preferenceDTO.getManglik().equalsIgnoreCase(p.getManglik())) matched++;
                    }

                    double matchPercent = (total == 0) ? 0 : (matched * 100.0 / total);
                    return matchPercent >= 60.0;
                })
                .map(ProfileMapper::toDTO)
                .collect(Collectors.toList());

        return matchedProfiles;
    }

//    public String uploadToS3(MultipartFile file) {
//        String bucketName = "your-bucket-name";
//        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(file.getSize());
//
//        amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), metadata));
//        return amazonS3.getUrl(bucketName, key).toString();
//    }
}
