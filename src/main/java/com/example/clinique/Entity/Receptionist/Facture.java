package com.example.clinique.Entity.Receptionist;




import com.example.clinique.Entity.Auth.Patient;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "Facture")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
        private String nom;





    public Facture(String date, Double montant, String description, String patient) {
        this.date = date;
        this.montant = montant;
        this.description = description;
        this.nom = patient;
    }  }