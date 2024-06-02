package com.example.clinique;

import com.example.clinique.Entity.Equipement.Categorie;
import com.example.clinique.Repositories.labo.AnalyseRepository;
import com.example.clinique.Repositories.labo.BilanRepository;
import com.example.clinique.Repositories.Dispositif.CategorieRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.security.Key;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CliniqueApplication implements CommandLineRunner {

    private final AnalyseRepository analyseRepository;
    private final BilanRepository bilanRepository;
    private final CategorieRepository categorieRepository;

    public static void main(String[] args) {
        SpringApplication.run(CliniqueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        addCategories();
        generateAndPrintKey();
    }

    private void addCategories() {
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

        categories.forEach(categoryName -> {
            Categorie categorie = new Categorie();
            categorie.setLibelle(categoryName);
            categorieRepository.save(categorie);
        });
    }

    private void generateAndPrintKey() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Votre clé sécurisée (encodée en Base64) : " + base64Key);
    }
}
