package com.example.mangalfera.dto;

public class LoginResponse {
    private final String token;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String role;


    public LoginResponse(String token, String email, String firstName, String lastName, String role) {
        this.token = token;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getToken() {
        return token;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
