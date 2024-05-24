package com.example.clinique.Controller;

import com.example.clinique.Entity.Receptionist.RendezVous;
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



}
