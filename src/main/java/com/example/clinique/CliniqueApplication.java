package com.example.clinique;

import com.example.clinique.Repositories.CategorieRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CliniqueApplication {

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
}
