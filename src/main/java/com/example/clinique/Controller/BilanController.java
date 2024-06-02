package com.example.clinique.Controller;

import com.example.clinique.DTO.BilanCreation.BilanCreationDTO;
import com.example.clinique.DTO.BilanCreation.GlycemieDTO;
import com.example.clinique.DTO.BilanDTO;
import com.example.clinique.Entity.laboratoire.Glycemie;
import com.example.clinique.Services.LaboratoireService;
import com.example.clinique.Entity.laboratoire.Bilan;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bilans")
public class BilanController {
    @Autowired
    private LaboratoireService laboratoireService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping()
    public Bilan createBilan(@RequestBody BilanCreationDTO bilanCreationDTO) {
        return laboratoireService.saveAllAnalysesAndBilan(
                bilanCreationDTO.analyses,
                bilanCreationDTO.dossierMedicalId
        );

    }
    @PutMapping("/{id}")
    public Bilan updateBilan(@PathVariable Long id, @RequestBody BilanCreationDTO bilanCreationDTO) {
        return laboratoireService.updateAllAnalysesAndBilan(bilanCreationDTO , id);
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

    @GetMapping()
    public ResponseEntity<List<Bilan>> getAllBilans() {
        List<Bilan> bilans = laboratoireService.getAllBilans();
        return new ResponseEntity<>(bilans, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilan(@PathVariable Long id) {
        laboratoireService.deleteBilan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
