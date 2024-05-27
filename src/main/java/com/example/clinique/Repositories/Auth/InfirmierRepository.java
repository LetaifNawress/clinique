package com.example.clinique.Repositories.Auth;

import com.example.clinique.Entity.Auth.Infirmier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfirmierRepository extends JpaRepository<Infirmier, Long> {
}