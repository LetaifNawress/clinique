package com.example.clinique.Services;

import com.example.clinique.DTO.Authentication.MedecinDTO;
import com.example.clinique.DTO.Authentication.PatientDTO;
import com.example.clinique.DTO.Authentication.UserRegistrationDTO;
import com.example.clinique.Entity.Auth.*;
import com.example.clinique.Entity.Doc.DossierMedical;
import com.example.clinique.Repositories.Auth.MedecinRepository;
import com.example.clinique.Repositories.Auth.PatientRepository;
import com.example.clinique.Repositories.Auth.UserRepository;
import com.example.clinique.Repositories.Doc.DossierMedicalRepository;
import com.example.clinique.config2.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final MedecinRepository medecinRepository;
  private final DossierMedicalRepository dossierMedicalRepository;
  private final PatientRepository patientRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public void register(UserRegistrationDTO registrationDTO) {
    User user;
    if ("DOCTOR".equals(registrationDTO.getRole()) || "INFERMIER".equals(registrationDTO.getRole())) {
      // Créer un médecin ou un infirmier

      if ("DOCTOR".equals(registrationDTO.getRole())) {
        // Créer un médecin
        Medecin medecin = new Medecin();
        medecin.setTitle(registrationDTO.getTitle());
        medecin.setDepartment(registrationDTO.getDepartment());
        user = medecin;
      } else {
        // Créer un infirmier
        Infirmier infirmier = new Infirmier();
        infirmier.setTitle(registrationDTO.getTitle());
        infirmier.setDepartment(registrationDTO.getDepartment());
        user = infirmier;
      }
    } else {
      // Créer un utilisateur ordinaire
      user = new ConcreteUser();
    }

    user.setEmail(registrationDTO.getEmail());
    user.setPassword(encryptPassword(registrationDTO.getPassword()));
    user.setCin(registrationDTO.getCin());
    user.setTele(registrationDTO.getTele());
    user.setUserName(registrationDTO.getUserName());
    user.setRole(registrationDTO.getRole());
    user.setDateOfBirth(registrationDTO.getDateOfBirth());

    userRepository.save(user);
  }

  private String encryptPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
  }

  public void addDoctor(MedecinDTO medecinDTO) {

    Medecin medecin = new Medecin();

    medecin.setEmail(medecinDTO.getEmail());
    medecin.setPassword(medecinDTO.getPassword());
    medecin.setCin(medecinDTO.getCin());
    medecin.setTele(medecinDTO.getTele());
    medecin.setUserName(medecinDTO.getUserName());
    medecin.setId(medecinDTO.getId());
    medecin.setDateOfBirth(medecinDTO.getDateOfBirth());
    medecin.setTitle(medecinDTO.getTitle());
    medecin.setDepartment(medecinDTO.getDepartment());
    medecinRepository.save(medecin);
  }
  public void addPatient(PatientDTO patientDTO) {
    Patient patient = new Patient();
    patient.setEmail(patientDTO.getEmail());
    patient.setPassword(patientDTO.getPassword());
    patient.setCin(patientDTO.getCin());
    patient.setTele(patientDTO.getTele());
    patient.setUserName(patientDTO.getUserName());
    patient.setId(patientDTO.getId());
    patient.setRole("PATIENT");
    patient.setDateOfBirth(patientDTO.getDateOfBirth());
    patient.setInsuranceNumber(patientDTO.getInsuranceNumber());

    DossierMedical dossierMedical = new DossierMedical();
    dossierMedical.setDateCreation(new Date());

    patient.setDossierMedical(dossierMedical);
    dossierMedical.setPatient(patient);

    patientRepository.save(patient);
  }
  public List<User> getAllUsersExceptPatients() {
    return userRepository.findAll()
            .stream()
            .filter(user -> !user.getRole().equals("PATIENT"))
            .collect(Collectors.toList());
  }



}
