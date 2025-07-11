package com.example.mangalfera.controller;

import com.example.mangalfera.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping()
public class FileUploadController {
    private final S3Service s3Service;

    public FileUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
////            String fileUrl = s3Service.uploadToS3(file);
//            return ResponseEntity.ok("File uploaded successfully! URL: ");
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
//        }
        return null;
    }
}
