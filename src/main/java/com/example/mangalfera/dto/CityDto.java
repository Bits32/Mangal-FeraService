package com.example.mangalfera.dto;

public class CityDto{
    private Long id;
    private String name;

    public CityDto(Long id, String name) {
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
