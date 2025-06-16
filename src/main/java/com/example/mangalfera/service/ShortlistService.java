package com.example.mangalfera.service;

import com.example.mangalfera.Mapper.ProfileMapper;
import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.model.Profile;
import com.example.mangalfera.model.Shortlist;
import com.example.mangalfera.model.User;

import com.example.mangalfera.repository.ShortlistRepository;
import com.example.mangalfera.security.LoggedInUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortlistService {
    @Autowired
    private ShortlistRepository shortlistRepository;

    @Autowired
    private UserService userService;

    public List<ProfileDTO> getShortlistedProfiles() {
        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return shortlistRepository.findByUserId(user.getId())
                .stream()
                .map(shortlist -> ProfileMapper.toDTO(shortlist.getProfile()))
                .collect(Collectors.toList());
    }

    public void addToShortlist(Long profileId) {

        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (shortlistRepository.existsByUserIdAndProfileId(user.getId(), profileId)) {
            return;
        }
        Shortlist shortlist = new Shortlist();
        user.setId(user.getId());

        Profile profile = new Profile();
        profile.setId(profileId);

        shortlist.setUser(user);
        shortlist.setProfile(profile);
        shortlist.setShortlistedAt(LocalDateTime.now());
        shortlist.setShortlistedBy(LoggedInUserUtil.getLoggedInEmail());

        shortlistRepository.save(shortlist);
    }

    @Transactional
    public void removeFromShortlist (Long profileId) {
        User user = userService.getUserByEmail(LoggedInUserUtil.getLoggedInEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        shortlistRepository.deleteByUserIdAndProfileId(user.getId(), profileId);
    }
}
