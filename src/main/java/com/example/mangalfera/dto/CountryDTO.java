package com.example.mangalfera.dto;

public class CountryDTO {
    private Long id;
    private String name;

    public CountryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
