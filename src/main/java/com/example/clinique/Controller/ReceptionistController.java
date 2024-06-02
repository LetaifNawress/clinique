package com.example.clinique.Controller;

import com.example.clinique.Entity.Auth.Patient;
import com.example.clinique.Entity.Receptionist.Facture;
import com.example.clinique.Entity.Receptionist.RendezVous;
import com.example.clinique.Repositories.Auth.PatientRepository;
import com.example.clinique.Repositories.Receptionist.FactureRepository;
import com.example.clinique.Repositories.Receptionist.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recep")
public class ReceptionistController {


    @Autowired
    private  RendezVousRepository rendezVousRepository;
    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/rendezvous/search")
    public ResponseEntity<List<RendezVous>> searchRendezVous(@RequestParam("date") String date,
                                                             @RequestParam("medecinId") Long medecinId) {
        List<RendezVous> rendezVousList = rendezVousRepository.findByDateAndMedecinId(date, medecinId);


        if (rendezVousList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(rendezVousList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable("id") Long id) {
        Optional<RendezVous> rendezVousOptional = rendezVousRepository.findById(id);
        return rendezVousOptional.map(rendezVous -> new ResponseEntity<>(rendezVous, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return new ResponseEntity<>(rendezVousList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RendezVous> createRendezVous(@RequestBody RendezVous rendezVous) {
        rendezVous.setEtat(false);
        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
        return new ResponseEntity<>(savedRendezVous, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable("id") Long id, @RequestBody RendezVous updatedRendezVous) {
        if (rendezVousRepository.existsById(id)) {
            updatedRendezVous.setId(id);
            RendezVous savedRendezVous = rendezVousRepository.save(updatedRendezVous);
            return new ResponseEntity<>(savedRendezVous, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable("id") Long id) {
        if (rendezVousRepository.existsById(id)) {
            rendezVousRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}/etat")
    public ResponseEntity<RendezVous> changeEtat(@PathVariable Long id, @RequestParam Boolean etat) {
        Optional<RendezVous> rendezVousOptional = rendezVousRepository.findById(id);
        if (rendezVousOptional.isPresent()) {
            RendezVous rendezVous = rendezVousOptional.get();
            rendezVous.setEtat(etat);
            rendezVousRepository.save(rendezVous);
            return ResponseEntity.ok(rendezVous);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVous>> getRendezVousByPatient(@PathVariable Long patientId) {
        List<RendezVous> rendezVousList = rendezVousRepository.findByPatientId(patientId);
        if (rendezVousList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(rendezVousList);
        }
    }




    @GetMapping("/facture/all")
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = factureRepository.findAll();
        if (factures.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/facture/{id}")
    public ResponseEntity<?> getFactureById(@PathVariable Long id) {
        Optional<Facture> facture = factureRepository.findById(id);
        if (facture.isPresent()) {
            return ResponseEntity.ok(facture.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Facture with id " + id + " not found.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Facture> addFacture(@RequestBody Facture facture) {
        Facture newFacture = factureRepository.save(facture);
        return new ResponseEntity<>(newFacture, HttpStatus.CREATED);
    }
    @PutMapping("/facture/{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable Long id, @RequestBody Facture updatedFacture) {
        if (!factureRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedFacture.setId(id);
        Facture updatedFactureEntity = factureRepository.save(updatedFacture);
        return new ResponseEntity<>(updatedFactureEntity, HttpStatus.OK);
    }

    @DeleteMapping("/facture/{id}")
    public ResponseEntity<?> deleteFacture(@PathVariable Long id) {
        if (factureRepository.existsById(id)) {
            factureRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Facture with id " + id + " not found.");
        }
    }

}
