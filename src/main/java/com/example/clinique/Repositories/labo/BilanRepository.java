package com.example.clinique.Repositories.labo;

import com.example.clinique.Entity.laboratoire.Bilan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilanRepository extends JpaRepository<Bilan, Long> {
    Bilan findBilanByDossiersMedical_Id(Long id);
}
