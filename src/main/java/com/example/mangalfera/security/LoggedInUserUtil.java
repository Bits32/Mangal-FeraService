package com.example.mangalfera.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedInUserUtil {
    public static CustomUserDetails getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails;
        }
        return null;
    }

    public static String getLoggedInEmail() {
        CustomUserDetails user = getLoggedInUser();
        return user != null ? user.getEmail() : null;
    }
}


