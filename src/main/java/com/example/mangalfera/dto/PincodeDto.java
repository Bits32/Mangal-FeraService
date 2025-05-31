package com.example.mangalfera.dto;

public class PincodeDto {
    private Long id;
    private String code;

    public PincodeDto(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
