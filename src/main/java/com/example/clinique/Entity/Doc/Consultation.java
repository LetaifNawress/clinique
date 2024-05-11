package com.example.clinique.Entity.Doc;

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

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String symptomes;

    @Column(nullable = false)
    private String diagnostic;

    @Column(nullable = false)
    private String ordonnance;
    public Consultation(DossierMedical dossierMedical, Date date, String symptomes, String diagnostic, String ordonnances) {
        this.dossierMedical = dossierMedical;
        this.date = date;
        this.symptomes = symptomes;
        this.diagnostic = diagnostic;
        this.ordonnance = ordonnances;
    }
}
