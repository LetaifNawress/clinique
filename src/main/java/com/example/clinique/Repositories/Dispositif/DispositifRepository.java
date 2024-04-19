package com.example.clinique.Repositories.Dispositif;

import com.example.clinique.Entity.Equipement.Diapositif_medicale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositifRepository extends JpaRepository<Diapositif_medicale, Long> {
    List<Diapositif_medicale> findAll();

}