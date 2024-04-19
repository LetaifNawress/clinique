package com.example.clinique.Entity.Equipement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@Table(name = "Article")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Diapositif_medicale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String ref;
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

    public Diapositif_medicale(String nom, String description, Fournisseur fournisseur, Categorie categorie, Integer quantite, Integer seuilMin, Integer seuilMax, String emplacement, String ref) {
        this.nom = nom;
        this.description = description;
        this.fournisseur = fournisseur;
        this.categorie = categorie;
        this.quantite = quantite;
        this.seuilMin = seuilMin;
        this.seuilMax = seuilMax;
        this.emplacement = emplacement;
        this.ref=ref;

    }
}
