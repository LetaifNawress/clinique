package com.example.clinique.Controller;

import com.example.clinique.DTO.BilanDTO;
import com.example.clinique.Entity.laboratoire.Bilan;
import com.example.clinique.Services.LaboratoireService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bilans")
public class BilanController {
    @Autowired
    private LaboratoireService laboratoireService;

    @Autowired
    private ObjectMapper objectMapper;

//    @PostMapping
//    public ResponseEntity<Bilan> createBilan(@RequestBody Bilan bilan) {
//        Bilan savedBilan = laboratoireService.saveBilan(bilan);
//        return new ResponseEntity<>(savedBilan, HttpStatus.CREATED);
//    }


//  @PostMapping("/glycemie")
//  public ResponseEntity<Bilan> addGlycemieToNewBilan(@RequestBody Glycemie glycemie) {
//      // Créer un nouveau bilan
//      Bilan bilan = new Bilan();
//
//      // Ajouter la glycémie au bilan
//      if (bilan.getGlycemies() == null) {
//          bilan.setGlycemies(new ArrayList<>());
//      }
//      glycemie.setBilan(bilan);
//      bilan.getGlycemies().add(glycemie);
//
//      // Enregistrer le nouveau bilan
//      laboratoireService.saveBilan(bilan);
//
//      // Retourner le nouveau bilan avec le statut OK
//      return new ResponseEntity<>(bilan, HttpStatus.OK);
//  }

    @PostMapping()
    public ResponseEntity<Bilan> createBilan() throws JsonProcessingException {
        Bilan bilan = new Bilan();
        Bilan savedBilan = laboratoireService.saveBilan(bilan);
        return new ResponseEntity<>(savedBilan, HttpStatus.CREATED);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Bilan> getBilan(@PathVariable Long id) {
        Bilan bilan = laboratoireService.getBilanById(id);
        if (bilan != null) {
            return new ResponseEntity<>(bilan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Bilan>> getAllBilans() {
        List<Bilan> bilans = laboratoireService.getAllBilans();
        return new ResponseEntity<>(bilans, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilan(@PathVariable Long id) {
        laboratoireService.deleteBilan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bilan> updateBilan(@PathVariable Long id, @RequestBody BilanDTO bilanDTO) {
        Bilan updatedBilan = laboratoireService.updateBilan(id, bilanDTO);
        if (updatedBilan != null) {
            return new ResponseEntity<>(updatedBilan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
