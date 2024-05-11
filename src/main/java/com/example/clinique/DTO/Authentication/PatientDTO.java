package com.example.clinique.DTO.Authentication;

import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO {
    private String email;
    private String password;
    private String cin;
    private String tele;
    private String userName;
    private Long id;
    private String role;
    private Date dateOfBirth;
    private String insuranceNumber;
    private String address;

}
