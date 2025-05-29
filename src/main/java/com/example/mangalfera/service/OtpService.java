package com.example.mangalfera.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    private static final int EXPIRY_MINUTES = 1;

    private static class OtpData {
        String otp;
        LocalDateTime createdAt;

        OtpData(String otp, LocalDateTime createdAt) {
            this.otp = otp;
            this.createdAt = createdAt;
        }
    }
    private final Map<String, OtpData> otpStore = new HashMap<>();

    public String generateOtp(String identifier) {
        String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
        otpStore.put(identifier, new OtpData(otp, LocalDateTime.now()));
        return otp;
    }

    public boolean verifyOtp(String identifier, String inputOtp) {
        OtpData otpData = otpStore.get(identifier);
        if (otpData == null) return false;
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(otpData.createdAt.plusMinutes(EXPIRY_MINUTES))) {
            otpStore.remove(identifier);
            return false;
        }
        return otpData.otp.equals(inputOtp);
    }

    public void clearOtp(String identifier) {
        otpStore.remove(identifier);
    }
}
