package com.example.clinique.Entity.Equipement;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@Table(name = "Article")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String photo;
    private String description;
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    private Integer quantite;
    private Integer seuilMin;
    private Integer seuilMax;
    private String emplacement;

    public Article(String nom, String description, Fournisseur fournisseur, Categorie categorie, Integer quantite, Integer seuilMin, Integer seuilMax, String emplacement, String photo) {
        this.nom = nom;
        this.description = description;
        this.fournisseur = fournisseur;
        this.categorie = categorie;
        this.quantite = quantite;
        this.seuilMin = seuilMin;
        this.seuilMax = seuilMax;
        this.emplacement = emplacement;
        this.photo = photo;
    }
}
