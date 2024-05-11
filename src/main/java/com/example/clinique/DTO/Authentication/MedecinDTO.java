package com.example.clinique.DTO.Authentication;

import java.util.Date;

import lombok.Data;

@Data
public class MedecinDTO {
    private String email;
    private String password;
    private String cin;
    private String tele;
    private String userName;
    private Long id;
    private Date dateOfBirth;
    private String title;
    private String department;
}
