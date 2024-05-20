package com.example.clinique.Entity.Receptionist;

import com.example.clinique.Entity.Auth.Medecin;
import com.example.clinique.Entity.Auth.Patient;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "RendezVous")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @Temporal(TemporalType.TIMESTAMP)
    private String date;

    private String heure;
    private Boolean etat ;
}
