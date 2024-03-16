package com.example.clinique.Entity.Equipement;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private Date dateCommande;
    @OneToMany
    private List<Article> articles;
    private String statut;
    // Getters and setters
}