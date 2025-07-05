package com.example.mangalfera.controller;

import com.example.mangalfera.dto.LoginRequest;
import com.example.mangalfera.dto.LoginResponse;
import com.example.mangalfera.dto.RegisterRequest;
import com.example.mangalfera.model.User;
import com.example.mangalfera.repository.UserRepository;
import com.example.mangalfera.security.JwtUtil;
import com.example.mangalfera.service.OtpService;
import com.example.mangalfera.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpService otpService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOtp = userRepository.findByEmail(loginRequest.getEmail());

        if(userOtp.isPresent()) {
            User user = userOtp.get();

            if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail(),user.getFirstName(), user.getLastName(), user.getRole());
                LoginResponse response = new LoginResponse(
                        "Bearer " + token,
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole()
                );
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password!");
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMiddleName(request.getMiddleName());
        user.setPassword(request.getPassword());
        user.setContactNumber(request.getContactNumber());
        user.setRole("User");
        user.setEmail(request.getEmail());
        user.setProfileCreatedFor(request.getProfileCreatedFor());
        user.setMembershipPlanId(request.getMembershipPlanId());
        user.setMembershipPlan(request.getMembershipPlan());
        user.setMembershipStartPlan(request.getMembershipStartPlan());
        user.setMembershipEndPlan(request.getMembershipEndPlan());
        user.setActive(true);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        userService.registerUser(user);
        return "User registered successfully!";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody RegisterRequest request) {
        try {
            userService.updateUser(id, request);
            return ResponseEntity.ok("User updated successfully!");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/update/plan/{id}")
    public ResponseEntity<String> updateUserPlan(@PathVariable Long id, @RequestBody RegisterRequest request) {
        try {
            userService.updateUserPlan(id, request);
            return ResponseEntity.ok("User updated Plan successfully!");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/update/view/contact/{id}")
    public ResponseEntity<String> updateUserViewContact(@PathVariable Long id, @RequestBody RegisterRequest request) {
        try {
            userService.updateUserViewContact(id, request);
            return ResponseEntity.ok("User updated Plan successfully!");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/update/photos/count/{id}")
    public ResponseEntity<String> updateUserPhotosCounts(@PathVariable Long id, @RequestBody RegisterRequest request) {
        try {
            userService.updateUserPhotosCounts(id, request);
            return ResponseEntity.ok("User updated Plan successfully!");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/update/videos/count/{id}")
    public ResponseEntity<String> updateUserVideosCounts(@PathVariable Long id, @RequestBody RegisterRequest request) {
        try {
            userService.updateUserVideosCounts(id, request);
            return ResponseEntity.ok("User updated Plan successfully!");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String identifier,
            @RequestParam String otp,
            @RequestParam String newPassword,
            @RequestParam String mode) {

        if (!otpService.verifyOtp(identifier, otp)) {
            return ResponseEntity.badRequest().body("Invalid or expired OTP.");
        }
        try {
            userService.resetPasswordByIdentifier(identifier, newPassword, mode);
            otpService.clearOtp(identifier);
            return ResponseEntity.ok("Password reset successfully!");
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
