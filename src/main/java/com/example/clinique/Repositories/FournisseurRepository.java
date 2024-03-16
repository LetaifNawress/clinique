package com.example.clinique.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FournisseurRepository  extends JpaRepository<Fournisseur, Long> {
    @Override
    Optional<Fournisseur> findById(Long aLong);
}
