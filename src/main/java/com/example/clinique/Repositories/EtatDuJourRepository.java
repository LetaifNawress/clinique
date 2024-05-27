package com.example.clinique.Repositories;

import com.example.clinique.Entity.Auth.Infirmier;
import com.example.clinique.Entity.Auth.Patient;
import com.example.clinique.Entity.EtatDuJour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtatDuJourRepository extends JpaRepository<EtatDuJour, Long> {
    List<EtatDuJour> findByPatient(Patient patient);
    List<EtatDuJour> findByInfirmier(Infirmier infirmier);
}
