package com.example.mangalfera.repository;

import com.example.mangalfera.model.Profile;

import java.util.List;

public interface CustomProfileRepository {
    List<Profile> findMatchingProfiles(Long currentUserId,
                                       Integer minAge,
                                       Integer maxAge,
                                       Integer minHeight,
                                       Integer maxHeight,
                                       String maritalStatus,
                                       String religion,
                                       String caste,
                                       String country,
                                       String state,
                                       String city,
                                       String manglik);
}
