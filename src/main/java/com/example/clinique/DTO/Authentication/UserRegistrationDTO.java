package com.example.clinique.DTO.Authentication;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegistrationDTO {
    private String email;
    private String password;
    private String cin;
    private String tele;
    private String userName;
    private String role;
    private Date dateOfBirth;
}
