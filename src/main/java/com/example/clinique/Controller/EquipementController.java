package com.example.clinique.Controller;

import com.example.clinique.DTO.ArticleDTO;
import com.example.clinique.DTO.FournisseurDTO;
import com.example.clinique.Entity.Equipement.Article;
import com.example.clinique.Entity.Equipement.Categorie;
import com.example.clinique.Entity.Equipement.Fournisseur;
import com.example.clinique.Repositories.CategorieRepository;
import com.example.clinique.Services.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/equipements")
public class EquipementController {
    @Autowired
    private EquipementService equipementService;
    @Autowired
    private CategorieRepository categoryRepository;

    // Méthodes pour la gestion des équipements

    @GetMapping("/equipements")
    public List<Article> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/{id}")
    public Article getEquipementById(@PathVariable Long id) {
        return equipementService.getEquipementById(id);
    }
    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        return equipementService.updateArticle(id, articleDTO);
    }

    @PostMapping("/equipements")
    public Article createEquipement(@RequestBody ArticleDTO equipementDTO) {
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
        return categoryRepository.findAll();
    }
}