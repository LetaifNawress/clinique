package com.example.clinique.Controller;

import com.example.clinique.Repositories.GlycemieRepository;
import com.example.clinique.Services.LaboratoireService;
import com.example.clinique.Entity.laboratoire.Glycemie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bi")

public class AnalyseController {

   @Autowired
    private LaboratoireService laboratoireService;

   /* @PostMapping("{bilanId}/glycemie")
    public ResponseEntity<Bilan> addGlycemieToBilan(@PathVariable Long bilanId, @RequestBody Glycemie glycemie) {
        Bilan bilan = laboratoireService.getBilanById(bilanId);
        if (bilan != null) {
            glycemie.setBilan(bilan);
            if (bilan.getGlycemies() == null) {
                bilan.setGlycemies(new ArrayList<>());
            }
            bilan.getGlycemies().add(glycemie);
            laboratoireService.saveBilan(bilan);
            return new ResponseEntity<>(bilan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{bilanId}/hemoglobine")
    public ResponseEntity<Bilan> addHemoglobineToBilan(@PathVariable Long bilanId, @RequestBody Hemoglobine hemoglobine) {
        Bilan bilan = laboratoireService.getBilanById(bilanId);
        if (bilan != null) {
            hemoglobine.setBilan(bilan);
            if (bilan.getHemoglobines() == null) {
                bilan.setHemoglobines(new ArrayList<>());
            }
            bilan.getHemoglobines().add(hemoglobine);
            laboratoireService.saveBilan(bilan);
            return new ResponseEntity<>(bilan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @Autowired
    private GlycemieRepository glycemieRepository;

    @GetMapping("/glycemie")
    public List<Glycemie> getAllGlycemie() {
        return glycemieRepository.findAll();
    }

    @GetMapping()
    public List<Glycemie> getGlycemieById() {
        return glycemieRepository.findAll();
    }

    @PostMapping("/glycemie")
    public Glycemie createGlycemie(@RequestBody Glycemie glycemie) {
        return glycemieRepository.save(glycemie);
    }

    @PutMapping("/glycemie/{id}")
    public Glycemie updateGlycemie(@PathVariable Long id, @RequestBody Glycemie glycemieDetails) {
        Glycemie glycemie = glycemieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Glycemie not found with id: " + id));

        glycemie.setGlycemie(glycemieDetails.getGlycemie());
        // Mettez à jour d'autres champs si nécessaire

        return glycemieRepository.save(glycemie);
    }

    @DeleteMapping("/glycemi/{id}")
    public void deleteGlycemie(@PathVariable Long id) {
        Glycemie glycemie = glycemieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Glycemie not found with id: " + id));

        glycemieRepository.delete(glycemie);
    }




}


