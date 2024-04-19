package com.example.clinique.Entity.Equipement;



import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


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
    private List<Diapositif_medicale> articles;
    private String statut;
    // Getters and setters
}