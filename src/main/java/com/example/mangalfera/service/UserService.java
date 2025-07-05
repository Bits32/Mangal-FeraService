package com.example.mangalfera.service;

import com.example.mangalfera.dto.RegisterRequest;
import com.example.mangalfera.model.User;
import com.example.mangalfera.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already registered with this email: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public void updateUser(Long id, RegisterRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMiddleName(request.getMiddleName());
        user.setContactNumber(request.getContactNumber());
        user.setEmail(request.getEmail());
        user.setProfileCreatedFor(request.getProfileCreatedFor());
        user.setMembershipPlanId(request.getMembershipPlanId());
        user.setMembershipPlan(request.getMembershipPlan());
        user.setMembershipStartPlan(request.getMembershipStartPlan());
        user.setMembershipEndPlan(request.getMembershipEndPlan());
        user.setUpdatedDate(new Date());
        userRepository.save(user);
    }

    public void updateUserPlan(Long id, RegisterRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        Date today = new Date();

        if (user.getMembershipEndPlan() != null && user.getMembershipEndPlan().before(today)) {
            user.setRemainingContactViews(0);
            user.setRemainingPhotos(0);
            user.setRemainingVideos(0);
        } else {
            user.setRemainingContactViews(user.getContactViewLimit() - user.getContactViewCount());
            user.setRemainingPhotos( user.getPhotosLimits() - user.getPhotosUseCounts());
            user.setRemainingVideos(user.getVideosLimit() - user.getVideosUseCounts());
        }
        user.setContactViewCount(0);
        user.setPhotosUseCounts(0);
        user.setVideosUseCounts(0);

        user.setMembershipPlanId(request.getMembershipPlanId());
        user.setMembershipPlan(request.getMembershipPlan());
        user.setMembershipStartPlan(request.getMembershipStartPlan());
        user.setMembershipEndPlan(request.getMembershipEndPlan());
        user.setContactViewLimit(request.getContactViewLimit());
        user.setPhotosLimits(request.getPhotosLimits());
        user.setVideosLimit(request.getVideosLimit());
        user.setUpdatedDate(new Date());
        userRepository.save(user);
    }

    public void updateUserViewContact(Long id, RegisterRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setContactViewCount(request.getContactViewCount());
        String newProfileId = request.getViewedProfileIds();
        if (newProfileId != null && !user.hasViewedProfile(newProfileId)) {
            user.addViewedProfileId(newProfileId);
        }
        user.setUpdatedDate(new Date());
        userRepository.save(user);
    }

    public void updateUserPhotosCounts(Long id, RegisterRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setPhotosUseCounts(request.getPhotosUseCounts());
        user.setUpdatedDate(new Date());
        userRepository.save(user);
    }

    public void updateUserVideosCounts(Long id, RegisterRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setVideosUseCounts(request.getVideosUseCounts());
        user.setUpdatedDate(new Date());
        userRepository.save(user);
    }


    public void resetPasswordByIdentifier(String identifier, String newPassword, String mode) {
        Optional<User> optionalUser;

        if ("email".equalsIgnoreCase(mode)) {
            optionalUser = userRepository.findByEmail(identifier);
        } else if ("sms".equalsIgnoreCase(mode) || "whatsapp".equalsIgnoreCase(mode)) {
            optionalUser = userRepository.findByContactNumber(identifier);
        } else {
            throw new UsernameNotFoundException("Invalid mode: " + mode);
        }

        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with provided " + mode));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
