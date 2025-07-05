package com.example.mangalfera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildDTO {
    private String name;
    private Integer age;
    private String education;
    private List<SubChildDTO> subChildren;
}
