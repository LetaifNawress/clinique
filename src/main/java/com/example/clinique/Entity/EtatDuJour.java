package com.example.clinique.Entity;
import com.example.clinique.Entity.Auth.Infirmier;
import com.example.clinique.Entity.Auth.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class EtatDuJour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "infirmier_id")
    private Infirmier infirmier;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String status;

    public EtatDuJour(Infirmier infirmier, Patient patient, String date, String status) {
        this.infirmier = infirmier;
        this.patient = patient;
        this.date = date;
        this.status = status;
    }
}