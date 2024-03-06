package com.example.clinique.Controller;

import com.example.clinique.DTO.BilanDTO;
import com.example.clinique.Services.LaboratoireService;
import com.example.clinique.laboratoire.Bilan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bilans")
public class BilanController {
    @Autowired
    private LaboratoireService laboratoireService;

    @PostMapping
    public ResponseEntity<Bilan> createBilan(@RequestBody Bilan bilan) {
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
