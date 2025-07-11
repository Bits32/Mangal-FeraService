package com.example.mangalfera.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.example.mangalfera.model.S3UploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${minio.bucketName}")
    private String bucketName;

    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public S3UploadResponse uploadToS3(MultipartFile file) throws IOException {
        if (!amazonS3.doesBucketExistV2("mangalfera")) {
            amazonS3.createBucket("mangalfera");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String key = UUID.randomUUID() + extension;

        byte[] bytes = file.getBytes();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);
        metadata.setContentType(file.getContentType());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        PutObjectRequest putObjectRequest = new PutObjectRequest("mangalfera", key, inputStream, metadata);

        amazonS3.putObject(putObjectRequest);

        String fileUrl = amazonS3.getUrl("mangalfera", key).toString();
        return new S3UploadResponse(fileUrl, key);
    }
   
    public S3Object getFileFromS3(String filename) {
        try {
            System.out.println(filename);
            return amazonS3.getObject(new GetObjectRequest("mangalfera", filename));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
