package com.example.clinique.Controller;

import com.example.clinique.DTO.Authentication.*;
import com.example.clinique.Entity.Auth.Infirmier;
import com.example.clinique.Entity.Auth.Medecin;
import com.example.clinique.Entity.Auth.Patient;
import com.example.clinique.Entity.Auth.User;
import com.example.clinique.Repositories.Auth.PatientRepository;
import com.example.clinique.Repositories.Auth.UserRepository;
import com.example.clinique.Services.AuthenticationService;
import com.example.clinique.config2.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    PatientRepository patientRepository ;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        service.register(registrationDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody MedecinDTO medecinDTO) {
        service.addDoctor(medecinDTO);
        return ResponseEntity.ok("Docteur ajouté avec succès !");
    }




    @PostMapping("/add")
    public ResponseEntity<String> addPatient(@RequestBody PatientDTO patientDTO) {
        service.addPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient ajouté avec succès !");
    }

    @GetMapping("/all")
    public List<User> getAllUsersExceptPatients() {
        return service.getAllUsersExceptPatients();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println("je suis ici : " + request.toString());
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);


        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String jwtToken = jwtService.generateToken(user);
            AuthenticationResponse response = AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentification échouée. Identifiants invalides.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRegistrationDTO userUpdate) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();


            user.setUserName(userUpdate.getUserName());
            user.setEmail(userUpdate.getEmail());
            if (!userUpdate.getPassword().isEmpty() && !passwordEncoder.matches(userUpdate.getPassword(), user.getPassword())) {
                // Hasher le nouveau mot de passe et le mettre à jour
                String hashedPassword = passwordEncoder.encode(userUpdate.getPassword());
                user.setPassword(hashedPassword);
            }
            user.setCin(userUpdate.getCin());
            user.setTele(userUpdate.getTele());
            user.setRole(userUpdate.getRole());
            user.setDateOfBirth(userUpdate.getDateOfBirth());



            if (user instanceof Medecin) {
                Medecin medecin = (Medecin) user;
                medecin.setTitle(userUpdate.getTitle());
                medecin.setDepartment(userUpdate.getDepartment());
            }
            else if (user instanceof Infirmier) {
                Infirmier infirmier = (Infirmier) user;
                // Mettre à jour les champs spécifiques des infirmiers si nécessaire
            }
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé avec l'ID : " + id);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
//Patient crud
@PostMapping("/patient")
public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
    Patient savedPatient = patientRepository.save(patient);
    return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
}
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            patient.setId(id);
            Patient user = patientOptional.get();


            user.setUserName(patient.getUserName());
            user.setEmail(patient.getEmail());
            if (!patient.getPassword().isEmpty() && !passwordEncoder.matches(patient.getPassword(), user.getPassword())) {

                String hashedPassword = passwordEncoder.encode(patient.getPassword());
                user.setPassword(hashedPassword);
            }
            user.setCin(patient.getCin());
            user.setTele(patient.getTele());
            user.setRole(patient.getRole());
            user.setDateOfBirth(patient.getDateOfBirth());
            user.setMedecins(patient.getMedecins());
            user.setAddress(patient.getAddress());
            user.setInsuranceNumber(patient.getInsuranceNumber());



            Patient updatedPatient = patientRepository.save(patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            patientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}





