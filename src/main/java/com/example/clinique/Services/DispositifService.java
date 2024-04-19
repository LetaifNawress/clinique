package com.example.clinique.Services;

import com.example.clinique.DTO.ArticleDTO;
import com.example.clinique.DTO.FournisseurDTO;
import com.example.clinique.Entity.Equipement.Diapositif_medicale;
import com.example.clinique.Entity.Equipement.Categorie;
import com.example.clinique.Entity.Equipement.Fournisseur;
import com.example.clinique.Repositories.Dispositif.DispositifRepository;
import com.example.clinique.Repositories.Dispositif.CategorieRepository;
import com.example.clinique.Repositories.Dispositif.FournisseurRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispositifService {



    @Autowired
    private DispositifRepository articleRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository; @Autowired
    private CategorieRepository categorieRepository ;

    public List<Diapositif_medicale> getAllEquipements() {
        return articleRepository.findAll();
    }

    public Diapositif_medicale getEquipementById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipement not found with id: " + id));
    }

    public Diapositif_medicale saveEquipement(ArticleDTO equipementDTO) {

        Fournisseur fournisseur = fournisseurRepository.findById(equipementDTO.getFournisseurId())
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found with id: " + equipementDTO.getFournisseurId()));


        Categorie categorie = categorieRepository.findById(equipementDTO.getCategorieId())
                .orElseThrow(() -> new EntityNotFoundException("Categorie not found with id: " + equipementDTO.getCategorieId()));


        Diapositif_medicale equipement = new Diapositif_medicale();
        equipement.setNom(equipementDTO.getNom());
        equipement.setRef(equipementDTO.getRef());
        equipement.setDescription(equipementDTO.getDescription());
        equipement.setFournisseur(fournisseur);
        equipement.setCategorie(categorie);
        equipement.setQuantite(equipementDTO.getQuantite());
        equipement.setSeuilMin(equipementDTO.getSeuilMin());
        equipement.setSeuilMax(equipementDTO.getSeuilMax());
        equipement.setEmplacement(equipementDTO.getEmplacement());

        // Enregistrer l'article dans le repository
        return articleRepository.save(equipement);
    }

    public void deleteEquipement(Long id) {
        articleRepository.deleteById(id);
    }




    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    public Fournisseur getFournisseurById(Long id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found with id: " + id));
    }



    public Fournisseur saveFournisseur(FournisseurDTO fournisseurDTO) {
        // Vérifier si les informations fournies sont complètes
        if (fournisseurDTO.getNom() == null || fournisseurDTO.getNom().isEmpty() ||
                fournisseurDTO.getAdresse() == null || fournisseurDTO.getAdresse().isEmpty() ||
                fournisseurDTO.getEmail() == null || fournisseurDTO.getEmail().isEmpty() ||
                fournisseurDTO.getTel() == null ) {
            throw new IllegalArgumentException("Les informations fournies sont incomplètes");
        }


        Fournisseur fournisseur = new Fournisseur(fournisseurDTO.getNom(), fournisseurDTO.getAdresse(), fournisseurDTO.getEmail(), fournisseurDTO.getTel());


        return fournisseurRepository.save(fournisseur);
    }
    public Diapositif_medicale updateArticle(Long id, ArticleDTO articleDTO) {
        Optional<Diapositif_medicale> optionalArticle = articleRepository.findById(id);

        Categorie categorie = categorieRepository.findById(articleDTO.getCategorieId())
                .orElseThrow(() -> new EntityNotFoundException("Categorie not found with id: " + articleDTO.getCategorieId()));
        Fournisseur fournisseur = fournisseurRepository.findById(articleDTO.getFournisseurId())
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found with id: " + articleDTO.getFournisseurId()));



        if (optionalArticle.isPresent()) {
            Diapositif_medicale article = optionalArticle.get();

            article.setNom(articleDTO.getNom());
            article.setDescription(articleDTO.getDescription());
            article.setFournisseur(fournisseur);
            article.setCategorie(categorie);
            article.setRef(articleDTO.getRef());
            article.setQuantite(articleDTO.getQuantite());
            article.setSeuilMin(articleDTO.getSeuilMin());
            article.setSeuilMax(articleDTO.getSeuilMax());
            article.setEmplacement(articleDTO.getEmplacement());

            return articleRepository.save(article);
        } else {

            throw new RuntimeException("Équipement non trouvé avec l'identifiant : " + id);
        }
    }

    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
    }
    public Fournisseur updateFournisseur(FournisseurDTO fournisseurDTO) {
        // Vérifie si le fournisseur existe dans la base de données
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found with id: " + fournisseurDTO.getId()));

        // Met à jour les informations du fournisseur avec celles fournies dans le DTO
        fournisseur.setNom(fournisseurDTO.getNom());
        fournisseur.setAdresse(fournisseurDTO.getAdresse());
        fournisseur.setEmail(fournisseurDTO.getEmail());
        fournisseur.setTel(fournisseurDTO.getTel());

        // Enregistre les modifications dans la base de données et retourne le fournisseur mis à jour
        return fournisseurRepository.save(fournisseur);
    }}
