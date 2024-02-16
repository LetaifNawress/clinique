package com.example.clinique.DTO;

import lombok.Data;

@Data
public class FournisseurDTO {
    private Long id;
    private String nom;
    private String adresse;
    private String email;
    private Long tel;
    private String photo;
}
