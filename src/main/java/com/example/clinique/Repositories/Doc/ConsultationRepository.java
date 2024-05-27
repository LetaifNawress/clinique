package com.example.clinique.Repositories.Doc;

import com.example.clinique.Entity.Auth.Medecin;
import com.example.clinique.Entity.Auth.Patient;
import com.example.clinique.Entity.Doc.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByMedecin(Medecin medecin);

    List<Consultation> findByPatient(Patient patient);

    List<Consultation> findByPatientAndMedecin(Patient patient, Medecin medecin);
}
