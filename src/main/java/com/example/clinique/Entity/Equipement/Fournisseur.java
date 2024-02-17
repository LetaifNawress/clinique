package com.example.clinique.Entity.Equipement;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "Fournisseur")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String email;
    private String tel;
    private String photo;
    public Fournisseur(String nom, String adresse, String email , String tel,String photo) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
    }
}
