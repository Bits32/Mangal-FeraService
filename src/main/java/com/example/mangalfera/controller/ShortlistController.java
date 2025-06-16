package com.example.mangalfera.controller;

import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.service.ShortlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shortlist")
public class ShortlistController {

    @Autowired
    private ShortlistService shortlistService;

    @PostMapping("/add/{profileId}")
    public ResponseEntity<String> addToShortlist(@PathVariable Long profileId) {
        shortlistService.addToShortlist(profileId);
        return ResponseEntity.ok("Profile added to shortlist.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> getShortlistedProfiles() {
        List<ProfileDTO> shortlistedProfiles = shortlistService.getShortlistedProfiles();
        return ResponseEntity.ok(shortlistedProfiles);
    }

    @DeleteMapping("/remove/{profileId}")
    public ResponseEntity<String> removeFromShortlist(@PathVariable Long profileId) {
        shortlistService.removeFromShortlist(profileId);
        return ResponseEntity.ok("Profile removed from shortlist.");
    }
}
