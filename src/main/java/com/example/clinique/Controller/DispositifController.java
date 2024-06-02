package com.example.clinique.Controller;

import com.example.clinique.DTO.ArticleDTO;
import com.example.clinique.DTO.FournisseurDTO;
import com.example.clinique.Entity.Equipement.Diapositif_medicale;
import com.example.clinique.Entity.Equipement.Categorie;
import com.example.clinique.Entity.Equipement.Fournisseur;
import com.example.clinique.Repositories.Dispositif.CategorieRepository;
import com.example.clinique.Services.DispositifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/equipements")
public class DispositifController {
    @Autowired
    private DispositifService equipementService;
    @Autowired
    private CategorieRepository categorieRepository;



    @GetMapping("/equipements")
    @PreAuthorize("hasRole('MANAGER')")
    public List<Diapositif_medicale> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/{id}")
    public Diapositif_medicale getEquipementById(@PathVariable Long id) {
        return equipementService.getEquipementById(id);
    }
    @PutMapping("/{id}")
    public Diapositif_medicale updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        return equipementService.updateArticle(id, articleDTO);
    }

    @PostMapping("/equipements")
    public Diapositif_medicale createEquipement(@RequestBody ArticleDTO equipementDTO) {
        return equipementService.saveEquipement(equipementDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
        return ResponseEntity.ok().build();
    }

    // Méthodes pour la gestion des fournisseurs

    @GetMapping("/fournisseurs")
    public List<Fournisseur> getAllFournisseurs() {
        return equipementService.getAllFournisseurs();
    }

    @GetMapping("/fournisseurs/{id}")
    public Fournisseur getFournisseurById(@PathVariable Long id) {
        return equipementService.getFournisseurById(id);
    }

    @PostMapping("/fournisseurs")
    public Fournisseur createFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
        return equipementService.saveFournisseur(fournisseurDTO);
    }

    @PutMapping("/fournisseurs/{id}")
    public Fournisseur updateFournisseur(@PathVariable Long id, @RequestBody FournisseurDTO fournisseurDTO) {
        fournisseurDTO.setId(id);
        return equipementService.updateFournisseur(fournisseurDTO);
    }

    @DeleteMapping("/fournisseurs/{id}")
    public ResponseEntity<?> deleteFournisseur(@PathVariable Long id) {
        equipementService.deleteFournisseur(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/categorie")
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
    @PostMapping("/categorie")
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie createdCategorie = categorieRepository.save(categorie);
        return new ResponseEntity<>(createdCategorie, HttpStatus.CREATED);
    }
    @DeleteMapping("(/categorie/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        if (categorieOptional.isPresent()) {
            categorieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Optional<Categorie> categorie = categorieRepository.findById(id);
        return categorie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/categorie/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie updatedCategorie) {
        Optional<Categorie> existingCategorieOptional = categorieRepository.findById(id);
        if (existingCategorieOptional.isPresent()) {
            Categorie existingCategorie = existingCategorieOptional.get();
            existingCategorie.setLibelle(updatedCategorie.getLibelle());
            Categorie savedCategorie = categorieRepository.save(existingCategorie);
            return new ResponseEntity<>(savedCategorie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}