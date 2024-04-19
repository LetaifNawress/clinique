package com.example.clinique;

import com.example.clinique.Entity.Equipement.Categorie;
import com.example.clinique.Repositories.labo.AnalyseRepository;
import com.example.clinique.Repositories.labo.BilanRepository;
import com.example.clinique.Repositories.Dispositif.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
//public class CliniqueApplication implements CommandLineRunner{
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

  //  @Override
  // public void run(String... args) throws Exception {
  //    // create bilan
    //    Bilan bilan = new Bilan();
    //  bilanRepository.save(bilan);
//
  //      // create analyse
  //    //create analyse de glycemie
    //    Glycemie glycemie = new Glycemie();
  //  glycemie.setGlycemie(1.2);
  //    //create analyse de transaminases
    //    Transaminase transaminase = new Transaminase();
    //  transaminase.setSgpt(12.2);
  //  transaminase.setSgot(85.2);
  //    //create analyse de lipidique
    //    Lipidique lipidique = new Lipidique();
    //  lipidique.setCholesterol(12.2);
    //  lipidique.setHdl(45.2);
    //  lipidique.setLdl(778.2);
    //  lipidique.setTriglycerides(99.2);
  //  lipidique.setVldl(66.2);
  //    // create analyse de hemoglobine
    //    Hemoglobine hemoglobine = new Hemoglobine();
    //  hemoglobine.setHemoglobine_glycosée(12.2);
    //  hemoglobine.setCreatinine(45.2);
  //  hemoglobine.setAcide_urique(778.2);
  //    //create analyse de ionogramme
    //    IonogrammeSanguin ionogrammeSanguin = new IonogrammeSanguin();
    //  ionogrammeSanguin.setSodium(12.2);
    //  ionogrammeSanguin.setPotassium(45.2);
    //  ionogrammeSanguin.setChlore(778.2);
    //  ionogrammeSanguin.setReserve_alcaline(99.2);
    //  ionogrammeSanguin.setTrou_anionique(66.2);
    //  ionogrammeSanguin.setProtides_totaux(66.2);
//

// Avant de sauvegarder le bilan
    //      List<Analyse> analyses = new ArrayList<>();
    //  analyses.add(glycemie);
    //  analyses.add(transaminase);
    //  analyses.add(lipidique);
    //  analyses.add(hemoglobine);
    //  analyses.add(ionogrammeSanguin);
    //  bilan.setAnalyses(analyses);




    //  glycemie.setBilan(bilan);
    //  bilan.setAnalyses(List.of(glycemie));

    //  transaminase.setBilan(bilan);
    //  bilan.setAnalyses(List.of(transaminase));

    //  lipidique.setBilan(bilan);
    //  bilan.setAnalyses(List.of(lipidique));

    //  hemoglobine.setBilan(bilan);
    //  bilan.setAnalyses(List.of(hemoglobine));

    //  ionogrammeSanguin.setBilan(bilan);
    //  bilan.setAnalyses(List.of(ionogrammeSanguin));
    //  // add analyse to list
    //  // bilan.getAnalyses().add(glycemie);

    //  analyseRepository.save(glycemie);
    //  analyseRepository.save(transaminase);
    //  analyseRepository.save(lipidique);
    //  analyseRepository.save(hemoglobine);
    //  analyseRepository.save(ionogrammeSanguin);
     //   bilanRepository.save(bilan);


    // }
}
