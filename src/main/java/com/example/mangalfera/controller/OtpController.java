package com.example.mangalfera.controller;

import com.example.mangalfera.dto.OtpRequest;
import com.example.mangalfera.model.User;
import com.example.mangalfera.repository.UserRepository;
import com.example.mangalfera.service.EmailService;
import com.example.mangalfera.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest request) {
        String contactNumber = request.getContactNumber();
        String email = request.getEmail();
        String mode = request.getMode();

        Optional<User> userOtp;

        if ("sms".equalsIgnoreCase(mode) || "whatsapp".equalsIgnoreCase(mode)) {
            if (contactNumber == null || contactNumber.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Contact number is required for " + mode + " mode.");
            }
            userOtp = userRepository.findByContactNumber(contactNumber);
        } else if ("email".equalsIgnoreCase(mode)) {
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required for email mode.");
            }
            userOtp = userRepository.findByEmail(email);
        } else {
            return ResponseEntity.badRequest().body("Invalid mode: " + mode);
        }

        if (userOtp.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found with provided " + mode);
        }


        String recipient = "email".equalsIgnoreCase(mode) ? email : contactNumber;
        String otp = otpService.generateOtp(recipient);
        String message = "Your OTP for password reset is: " + otp;

        boolean sent = false;

        switch (mode.toLowerCase()) {
            case "sms":
                // sent = smsService.sendSms(contactNumber, message);
                break;

            case "email":
                String to = email;
                String subject = "OTP for Password Reset";
                String from = "17@bharatinfotechSolutions.com";
                emailService.sendEmail(to, subject, from, message);
                sent = true;
                break;

            case "whatsapp":
                // sent = whatsappService.sendMessage(contactNumber, message);
                break;

            default:
                return ResponseEntity.badRequest().body("Invalid mode. Supported modes: sms, email, whatsapp");
        }

        if (sent) {
            return ResponseEntity.ok("OTP sent successfully via " + mode + "!");
        } else {
            return ResponseEntity.status(500).body("Failed to send OTP via " + mode);
        }
    }

    @PutMapping("/verify")
    public ResponseEntity<String> verifyOtpAndResetPassword(
            @RequestParam String identifier,
            @RequestParam String otp) {

        if (otpService.verifyOtp(identifier, otp)) {
            return ResponseEntity.ok("OTP is valid");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
    }

}
