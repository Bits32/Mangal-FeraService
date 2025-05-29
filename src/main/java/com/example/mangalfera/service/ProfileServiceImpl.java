package com.example.mangalfera.service;

import com.example.mangalfera.Mapper.ProfileMapper;
import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.model.Profile;
import com.example.mangalfera.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService  {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile profile = ProfileMapper.toEntity(profileDTO);
        Profile savedProfile = profileRepository.save(profile);
        return ProfileMapper.toDTO(savedProfile);
    }

    @Override
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Optional<Profile> existing = profileRepository.findById(id);
        if (existing.isPresent()) {
            Profile profile = ProfileMapper.toEntity(profileDTO);
            profile.setId(id);
            Profile updatedProfile = profileRepository.save(profile);
            return ProfileMapper.toDTO(updatedProfile);
        } else {
            throw new RuntimeException("Profile not found with id: " + id);
        }
    }

    @Override
    public ProfileDTO getProfileById(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
        return ProfileMapper.toDTO(profile);
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(ProfileMapper::toDTO)
                .collect(Collectors.toList());
    }

}
