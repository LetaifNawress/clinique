package com.example.clinique.Repositories.Receptionist;

import com.example.clinique.Entity.Receptionist.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
}
