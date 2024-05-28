package com.example.clinique.Controller;

import com.example.clinique.Entity.EtatDuJour;
import com.example.clinique.Entity.Auth.Infirmier;
import com.example.clinique.Entity.Auth.Patient;

import com.example.clinique.Repositories.Auth.InfirmierRepository;
import com.example.clinique.Repositories.Auth.PatientRepository;
import com.example.clinique.Repositories.EtatDuJourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infer")
public class InfirmierController {

    @Autowired
    private EtatDuJourRepository etatDuJourRepository;

    @Autowired
    private InfirmierRepository infirmierRepository;

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addEtatDuJour(@RequestParam Long infirmierId, @RequestParam Long patientId, @RequestParam String date, @RequestParam String status) {
        Optional<Infirmier> optionalInfirmier = infirmierRepository.findById(infirmierId);
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (optionalInfirmier.isEmpty()) {
            return ResponseEntity.badRequest().body("Infirmier not found");
        }
        if (optionalPatient.isEmpty()) {
            return ResponseEntity.badRequest().body("Patient not found");
        }

        Infirmier infirmier = optionalInfirmier.get();
        Patient patient = optionalPatient.get();

        EtatDuJour etatDuJour = new EtatDuJour(infirmier, patient, date, status);
        etatDuJour = etatDuJourRepository.save(etatDuJour);
        return ResponseEntity.ok(etatDuJour);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getEtatsDuJourByPatient(@PathVariable Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isEmpty()) {
            return ResponseEntity.badRequest().body("Patient not found");
        }

        Patient patient = optionalPatient.get();
        List<EtatDuJour> etatsDuJour = etatDuJourRepository.findByPatient(patient);
        return ResponseEntity.ok(etatsDuJour);
    }

    @GetMapping("/infirmier/{infirmierId}")
    public ResponseEntity<?> getEtatsDuJourByInfirmier(@PathVariable Long infirmierId) {
        Optional<Infirmier> optionalInfirmier = infirmierRepository.findById(infirmierId);
        if (optionalInfirmier.isEmpty()) {
            return ResponseEntity.badRequest().body("Infirmier not found");
        }

        Infirmier infirmier = optionalInfirmier.get();
        List<EtatDuJour> etatsDuJour = etatDuJourRepository.findByInfirmier(infirmier);
        return ResponseEntity.ok(etatsDuJour);
    }
}