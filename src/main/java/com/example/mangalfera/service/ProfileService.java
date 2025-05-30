package com.example.mangalfera.service;

import com.example.mangalfera.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {

    ProfileDTO createProfile(ProfileDTO profileDTO);
    ProfileDTO updateProfile(Long id, ProfileDTO profileDTO);
    ProfileDTO getProfileById(Long id);
//    ProfileDTO getProfileById(String email);
    List<ProfileDTO> getAllProfiles();
}
