package com.example.clinique.Repositories.Receptionist;

import com.example.clinique.Entity.Receptionist.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

}
