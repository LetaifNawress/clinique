package com.example.clinique;

import com.example.clinique.Entity.Equipement.Categorie;
import com.example.clinique.Repositories.AnalyseRepository;
import com.example.clinique.Repositories.BilanRepository;
import com.example.clinique.Repositories.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
// public class CliniqueApplication implements CommandLineRunner{
public class CliniqueApplication {

    private final AnalyseRepository analyseRepository;
    private final BilanRepository bilanRepository;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CliniqueApplication.class, args);

        // Récupérer le CategorieRepository à partir du contexte Spring
        CategorieRepository categorieRepository = context.getBean(CategorieRepository.class);

        // Liste des noms de catégories à ajouter
        List<String> categories = Arrays.asList(
            "Équipements médicaux généraux",
            "Équipements de diagnostic",
            "Équipements de laboratoire",
            "Équipements dentaires",
            "Équipements pour les salles d'opération",
            "Équipements de réadaptation et de physiothérapie",
            "Équipements de soins intensifs",
            "Équipements de stockage et de gestion des médicaments",
            "Équipements pour les soins ambulatoires"
        );

        // Ajouter chaque catégorie à la base de données
        categories.forEach(categoryName -> {
            Categorie categorie = new Categorie();
            categorie.setLibelle(categoryName);
            categorieRepository.save(categorie);
        });
    }

   // @Override
//    public void run(String... args) throws Exception {
//        // create bilan
//        Bilan bilan = new Bilan();
//        bilanRepository.save(bilan);
//
//        // create analyse
//        Glycemie glycemie = new Glycemie();
//        glycemie.setGlycemie(1.2);
//
//        glycemie.setBilan(bilan);
//        bilan.setAnalyses(List.of(glycemie));
//
//        // add analyse to list
//        // bilan.getAnalyses().add(glycemie);
//
//        analyseRepository.save(glycemie);
////		bilanRepository.save(bilan);
////
//
//    }
}
