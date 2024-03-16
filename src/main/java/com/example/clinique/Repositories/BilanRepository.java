package com.example.clinique.Repositories;

import com.example.clinique.Entity.laboratoire.Bilan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilanRepository extends JpaRepository<Bilan, Long> {
}
