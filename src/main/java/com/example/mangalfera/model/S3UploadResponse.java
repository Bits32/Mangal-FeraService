package com.example.mangalfera.model;

public class S3UploadResponse {
    private String fileUrl;
    private String filename; // this is the S3 key

    public S3UploadResponse(String fileUrl, String filename) {
        this.fileUrl = fileUrl;
        this.filename = filename;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getFilename() {
        return filename;
    }
}
