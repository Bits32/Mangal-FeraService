package com.example.mangalfera.dto;

public class PhotoResponseDTO {
    private String filename;
    private boolean permissionImage;

    public PhotoResponseDTO(String filename, boolean permissionImage) {
        this.filename = filename;
        this.permissionImage = permissionImage;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isPermissionImage() {
        return permissionImage;
    }

    public void setPermissionImage(boolean permissionImage) {
        this.permissionImage = permissionImage;
    }
}
