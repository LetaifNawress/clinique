package com.example.clinique.Repositories.Receptionist;

import com.example.clinique.Entity.Receptionist.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByDateAndMedecinId(String date, Long medecinId);
}
