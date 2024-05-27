package com.example.clinique.Entity.Doc;

import com.example.clinique.Entity.Auth.Medecin;
import com.example.clinique.Entity.Auth.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dossier_medical_id", referencedColumnName = "id")
    private DossierMedical dossierMedical;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String symptomes;

    @Column(nullable = false)
    private String diagnostic;

    @Column(nullable = false)
    private String ordonnance;
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    public Consultation(DossierMedical dossierMedical, String date, String symptomes, String diagnostic, String ordonnances,Medecin medecin,Patient patient) {
        this.dossierMedical = dossierMedical;
        this.date = date;
        this.symptomes = symptomes;
        this.diagnostic = diagnostic;
        this.ordonnance = ordonnances;
        this.medecin=medecin;
        this.patient=patient;
    }
}
