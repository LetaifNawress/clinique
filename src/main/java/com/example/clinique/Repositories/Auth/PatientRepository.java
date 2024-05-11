package com.example.clinique.Repositories.Auth;

import com.example.clinique.Entity.Auth.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository< Patient, Long> {

}
