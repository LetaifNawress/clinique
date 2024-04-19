package com.example.clinique.Repositories.Dispositif;

import com.example.clinique.Entity.Equipement.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
