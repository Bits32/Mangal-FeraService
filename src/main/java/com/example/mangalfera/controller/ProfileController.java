package com.example.mangalfera.controller;

import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.model.Photo;
import com.example.mangalfera.model.Profile;
import com.example.mangalfera.model.Video;
import com.example.mangalfera.repository.PhotoRepository;
import com.example.mangalfera.repository.ProfileRepository;
import com.example.mangalfera.repository.VideoRepository;
import com.example.mangalfera.security.LoggedInUserUtil;
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
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private VideoRepository videoRepository;

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
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam Long profileId,
                                             @RequestParam Long userId) {
        try {
            String uploadDir = "C:/uploaded-files/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            Photo photo = new Photo();
            photo.setFilename(fileName);
            photo.setPath(String.valueOf(filePath));
            photo.setCreatedBy(LoggedInUserUtil.getLoggedInEmail());
            photo.setCreatedDate(new Date());
            photo.setPermissionImage(false);
            photo.setUserId(userId);
            Profile profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new RuntimeException("Profile not found"));
            photo.setProfile(profile);
            photoRepository.save(photo);
            return ResponseEntity.ok("File uploaded & saved successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("/gallery/{profileId}")
    public ResponseEntity<List<Photo>> getUploadedPhotos(@PathVariable Long profileId) {
        List<Photo> photos = photoRepository.findByProfileId(profileId);
        return ResponseEntity.ok(photos);
//        List<String> filenames = photos.stream()
//                .map(Photo::getFilename)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(filenames);
    }

    @PutMapping("/gallery/accept/{photoId}")
    public ResponseEntity<String> acceptPhoto(@PathVariable Long photoId) {
        Optional<Photo> optionalPhoto = photoRepository.findById(photoId);
        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();
            photo.setPermissionImage(true);
            photoRepository.save(photo);
            return ResponseEntity.ok("Photo accepted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/gallery/reject/{photoId}")
    public ResponseEntity<String> rejectPhoto(@PathVariable Long photoId) {
        Optional<Photo> optionalPhoto = photoRepository.findById(photoId);
        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();
            photo.setPermissionImage(false);
            photoRepository.save(photo);
            return ResponseEntity.ok("Photo rejected.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/gallery/photos")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> photos = photoRepository.findAll();
        return ResponseEntity.ok(photos);
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

    @PostMapping("/upload-video")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file,
                                              @RequestParam Long profileId,
                                              @RequestParam Long userId) {
        try {
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("video/")) {
                return ResponseEntity.badRequest().body("Invalid file type. Only videos are allowed.");
            }

            String uploadDir = "C:/uploaded-videos/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            Video video = new Video();
            video.setFilename(fileName);
            video.setPath(filePath.toString());
            video.setUserId(userId);
            Profile profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new RuntimeException("Profile not found"));
            video.setProfile(profile);
            video.setPermissionVideo(false);
            video.setCreatedBy(LoggedInUserUtil.getLoggedInEmail());
            video.setCreatedDate(new Date());
            videoRepository.save(video);

            return ResponseEntity.ok("Video uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Video upload failed");
        }
    }

    @GetMapping("/video-gallery/{profileId}")
    public ResponseEntity<List<String>> getUploadedVideos(@PathVariable Long profileId) {
        List<Video> videos = videoRepository.findByProfileId(profileId);
        List<String> filenames = videos.stream()
                .map(Video::getFilename)
                .collect(Collectors.toList());
        return ResponseEntity.ok(filenames);
    }

    @GetMapping("/all/video-gallery")
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/videoFiles/{filename:.+}")
    public ResponseEntity<Resource> getVideoFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("C:/uploaded-videos/").resolve(filename).normalize();
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
