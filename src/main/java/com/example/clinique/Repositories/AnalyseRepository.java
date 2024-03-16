package com.example.clinique.Repositories;

import com.example.clinique.Entity.laboratoire.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyseRepository extends JpaRepository<Analyse, Long> {
}
