package org.example.jpademo.dto;

public record UserSignupDto (
    String username,
    String email,
    String password,
    String confirmPassword
){}
