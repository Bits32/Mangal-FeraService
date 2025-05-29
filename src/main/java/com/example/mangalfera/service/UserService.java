package com.example.mangalfera.service;

import com.example.mangalfera.model.User;
import com.example.mangalfera.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
