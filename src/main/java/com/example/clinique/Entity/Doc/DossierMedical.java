package com.example.clinique.Entity.Doc;


import com.example.clinique.Entity.Auth.Patient;
import com.example.clinique.Entity.laboratoire.Bilan;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class DossierMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @JsonBackReference
    private Patient patient;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "dossiersMedical", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Bilan> bilans;

    @Column(nullable = true)
    private String allergies;

    @Column(nullable = true)
    private String maladiesChroniques;

    @Column(nullable = true)
    private String numeroTelephoneProche;
    @Column(nullable = true)
    private String sexe;

    @Column(nullable = true)
    private String statutSocial;

    @Column(nullable = true)
    private double taille;

    @Column(nullable = true)
    private double poids;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreation;


    public DossierMedical(Patient patient, List<Consultation> consultations, String allergies, String maladiesChroniques, String numeroTelephoneProche, String sexe, String statutSocial, double taille, double poids) {
        this.patient = patient;
        this.consultations = consultations;
        this.allergies = allergies;
        this.maladiesChroniques = maladiesChroniques;
        this.numeroTelephoneProche = numeroTelephoneProche;
        this.sexe = sexe;
        this.statutSocial = statutSocial;
        this.taille = taille;
        this.poids = poids;
        this.dateCreation = new Date();
    }
}
