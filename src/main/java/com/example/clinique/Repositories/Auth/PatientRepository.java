package com.example.clinique.Repositories.Auth;

import com.example.clinique.Entity.Auth.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository< Patient, Long> {

}
