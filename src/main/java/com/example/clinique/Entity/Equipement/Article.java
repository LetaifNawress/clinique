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
@JsonSerialize
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Article(String nom, String description, Fournisseur fournisseur, Categorie categorie) {
        this.nom = nom;
        this.description = description;
        this.fournisseur = fournisseur;
        this.categorie = categorie;
    }
}