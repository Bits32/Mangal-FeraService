package com.example.mangalfera.dto;

public class StateDto {
    private Long id;
    private String name;

    public StateDto(Long id, String name) {
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
