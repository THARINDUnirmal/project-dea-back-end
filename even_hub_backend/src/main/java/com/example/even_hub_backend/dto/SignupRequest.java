package com.example.even_hub_backend.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String userName;
    private String userEmail;
    private String password;
    private String role; 
}
