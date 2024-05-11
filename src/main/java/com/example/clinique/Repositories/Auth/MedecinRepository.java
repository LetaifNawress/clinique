package com.example.clinique.Repositories.Auth;

import com.example.clinique.Entity.Auth.Medecin;
import com.example.clinique.Entity.Auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}
