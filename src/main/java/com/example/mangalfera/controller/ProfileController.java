package com.example.mangalfera.controller;

import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO createdProfile = profileService.createProfile(profileDTO);
        return ResponseEntity.ok(createdProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) {
        ProfileDTO updatedProfile = profileService.updateProfile(id,profileDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
        ProfileDTO profileDTO = profileService.getProfileById(id);
        return ResponseEntity.ok(profileDTO);
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<ProfileDTO> getUserById(@PathVariable Long id) {
        ProfileDTO profileDTO = profileService.getUserById(id);
        return ResponseEntity.ok(profileDTO);
    }

//    @GetMapping("/{email}")
//    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable String email) {
//        ProfileDTO profileDTO = profileService.getProfileById(email);
//        return ResponseEntity.ok(profileDTO);
//    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        List<ProfileDTO> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/matchPartnerPreference")
    public ResponseEntity<List<ProfileDTO>> getMatchedProfiles() {
        List<ProfileDTO> matchedProfiles = profileService.getMatchedProfiles();
        return ResponseEntity.ok(matchedProfiles);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "C:/uploaded-files/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            // Optionally return the file path or URL
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("/gallery")
    public ResponseEntity<List<String>> getUploadedPhotos() {
        File folder = new File("C:/uploaded-files/");
        String[] files = folder.list((dir, name) -> name.matches(".*\\.(jpg|png|jpeg|gif|webp)$"));
        return ResponseEntity.ok(Arrays.asList(files));
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("C:/uploaded-files/").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // Handle .webp content-type
            String contentType = Files.probeContentType(filePath);
            if (contentType == null && filename.toLowerCase().endsWith(".webp")) {
                contentType = "image/webp";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
