package com.example.clinique.Repositories;

import com.example.clinique.Entity.laboratoire.Glycemie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlycemieRepository extends JpaRepository<Glycemie, Long> {
}
