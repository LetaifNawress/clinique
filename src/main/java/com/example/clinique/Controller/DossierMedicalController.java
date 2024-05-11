package com.example.clinique.Controller;

import com.example.clinique.Entity.Doc.DossierMedical;
import com.example.clinique.Repositories.Doc.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dossier-medical")
public class DossierMedicalController {


    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getDossierMedicalByPatientId(@PathVariable Long patientId) {
        try {
            Optional<DossierMedical> dossierMedicalOptional = dossierMedicalRepository.findByPatientId(patientId);
            if (dossierMedicalOptional.isPresent()) {
                return new ResponseEntity<>(dossierMedicalOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Dossier médical introuvable pour l'ID du patient : " + patientId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite lors de la recherche du dossier médical", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossierMedical(@PathVariable Long id) {
        Optional<DossierMedical> dossierMedicalOptional = dossierMedicalRepository.findById(id);
        if (dossierMedicalOptional.isPresent()) {
            dossierMedicalRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<DossierMedical> createDossierMedical(@RequestBody DossierMedical dossierMedical) {
        DossierMedical savedDossierMedical = dossierMedicalRepository.save(dossierMedical);
        return new ResponseEntity<>(savedDossierMedical, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierMedical> updateDossierMedical(@PathVariable Long id, @RequestBody DossierMedical dossierMedicalDetails) {
        Optional<DossierMedical> dossierMedicalOptional = dossierMedicalRepository.findById(id);
        if (dossierMedicalOptional.isPresent()) {
            DossierMedical dossierMedical = dossierMedicalOptional.get();
            dossierMedical.setConsultations(dossierMedicalDetails.getConsultations());
            dossierMedical.setAllergies(dossierMedicalDetails.getAllergies());
            dossierMedical.setMaladiesChroniques(dossierMedicalDetails.getMaladiesChroniques());
            dossierMedical.setNumeroTelephoneProche(dossierMedicalDetails.getNumeroTelephoneProche());
            dossierMedical.setSexe(dossierMedicalDetails.getSexe());
            dossierMedical.setStatutSocial(dossierMedicalDetails.getStatutSocial());
            dossierMedical.setTaille(dossierMedicalDetails.getTaille());
            dossierMedical.setPoids(dossierMedicalDetails.getPoids());

            DossierMedical updatedDossierMedical = dossierMedicalRepository.save(dossierMedical);

            return new ResponseEntity<>(updatedDossierMedical, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}