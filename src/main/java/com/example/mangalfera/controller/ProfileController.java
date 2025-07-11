package com.example.mangalfera.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.example.mangalfera.dto.ProfileDTO;
import com.example.mangalfera.dto.SearchRequest;
import com.example.mangalfera.model.Photo;
import com.example.mangalfera.model.Profile;
import com.example.mangalfera.model.S3UploadResponse;
import com.example.mangalfera.model.Video;
import com.example.mangalfera.repository.PhotoRepository;
import com.example.mangalfera.repository.ProfileRepository;
import com.example.mangalfera.repository.VideoRepository;
import com.example.mangalfera.security.LoggedInUserUtil;
import com.example.mangalfera.service.ProfileService;
import com.example.mangalfera.service.S3Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
import java.io.InputStream;
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

    @Autowired
    private S3Service s3Service;

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
            S3UploadResponse response = s3Service.uploadToS3(file);

            Photo photo = new Photo();
            photo.setFilename(file.getOriginalFilename());
            photo.setFilename(response.getFilename());
            photo.setCreatedBy(LoggedInUserUtil.getLoggedInEmail());
            photo.setCreatedDate(new Date());
            photo.setPermissionImage(false);
            photo.setUserId(userId);
            Profile profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new RuntimeException("Profile not found"));
            photo.setProfile(profile);
            photoRepository.save(photo);
            return ResponseEntity.ok("File uploaded & saved successfully: " + response.getFileUrl());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("/gallery/{profileId}")
    public ResponseEntity<List<Photo>> getUploadedPhotos(@PathVariable Long profileId) {
        List<Photo> photos = photoRepository.findByProfileId(profileId);
        return ResponseEntity.ok(photos);
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

    @Transactional
    @DeleteMapping("/gallery/delete/{photoId}")
    public ResponseEntity<String> deletePhoto(@PathVariable Long photoId) {
        photoRepository.deleteById(photoId);
        return ResponseEntity.ok("Photo deleted successfully");
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        System.out.println("Fetching image: " + filename);
        try {
            S3Object s3Object = s3Service.getFileFromS3(filename);

            if (s3Object == null) {
                System.out.println("Image not found in S3: " + filename);
                return ResponseEntity.notFound().build();
            }

            InputStream inputStream = s3Object.getObjectContent();
            Resource resource = new InputStreamResource(inputStream);

            String contentType = s3Object.getObjectMetadata().getContentType();
            if (contentType == null && filename.toLowerCase().endsWith(".webp")) {
                contentType = "image/webp";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (Exception ex) {
            ex.printStackTrace();
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

            S3UploadResponse response = s3Service.uploadToS3(file);

            Video video = new Video();
            video.setFilename(response.getFilename());
            video.setPath(response.getFileUrl());
            video.setUserId(userId);
            Profile profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new RuntimeException("Profile not found"));
            video.setProfile(profile);
            video.setPermissionVideo(false);
            video.setCreatedBy(LoggedInUserUtil.getLoggedInEmail());
            video.setCreatedDate(new Date());
            videoRepository.save(video);

            return ResponseEntity.ok("Video uploaded successfully: " + response.getFileUrl());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Video upload failed");
        }
    }

    @GetMapping("/video-gallery/{profileId}")
    public ResponseEntity<List<Map<String, Object>>> getUploadedVideos(@PathVariable Long profileId) {
        List<Video> videos = videoRepository.findByProfileId(profileId);

        List<Map<String, Object>> response = videos.stream().map(video -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", video.getId());
            map.put("filename", video.getFilename());
            map.put("permissionVideo", video.getPermissionVideo());
            map.put("createdDate", video.getCreatedDate());
            map.put("createdBy", video.getCreatedBy());
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }


    @PutMapping("/video/accept/{videoId}")
    public ResponseEntity<String> acceptVideo(@PathVariable Long videoId) {
        Optional<Video> optionalVideo = videoRepository.findById(videoId);
        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();
            video.setPermissionVideo(true);
            videoRepository.save(video);
            return ResponseEntity.ok("Video accepted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/video/reject/{videoId}")
    public ResponseEntity<String> rejectVideo(@PathVariable Long videoId) {
        Optional<Video> optionalVideo = videoRepository.findById(videoId);
        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();
            video.setPermissionVideo(false);
            videoRepository.save(video);
            return ResponseEntity.ok("Video rejected.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all/video-gallery")
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        return ResponseEntity.ok(videos);
    }

    @Transactional
    @DeleteMapping("/video-gallery/delete/{videoId}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long videoId) {
        videoRepository.deleteById(videoId);
        return ResponseEntity.ok("Video deleted successfully");
    }

    @GetMapping("/videoFiles/{filename:.+}")
    public ResponseEntity<Resource> getVideoFile(@PathVariable String filename) {
        System.out.println("Fetching video: " + filename);
        try {
            S3Object s3Object = s3Service.getFileFromS3(filename);

            if (s3Object == null) {
                System.out.println("Video not found in S3: " + filename);
                return ResponseEntity.notFound().build();
            }

            InputStream inputStream = s3Object.getObjectContent();
            Resource resource = new InputStreamResource(inputStream);

            String contentType = s3Object.getObjectMetadata().getContentType();

            // Default content type fallback
            if (contentType == null) {
                if (filename.toLowerCase().endsWith(".mp4")) {
                    contentType = "video/mp4";
                } else {
                    contentType = "application/octet-stream";
                }
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("/search")
    public ResponseEntity<List<ProfileDTO>> searchProfiles(@RequestBody SearchRequest request) {
        List<ProfileDTO> results = profileService.searchProfiles(request);
        return ResponseEntity.ok(results);
    }


}
