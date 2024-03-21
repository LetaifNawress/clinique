// LaboratoireService.java
package com.example.clinique.Services;

import com.example.clinique.DTO.BilanCreation.*;
import com.example.clinique.DTO.BilanDTO;
import com.example.clinique.Entity.laboratoire.*;
import com.example.clinique.Repositories.AnalyseRepository;
import com.example.clinique.Repositories.BilanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BinaryOperator;

@Service
public class LaboratoireService {
    @Autowired
    private BilanRepository bilanRepository;

    @Autowired
    private AnalyseRepository analyseRepository;

    public Bilan saveBilan(Bilan bilan) {
        return bilanRepository.save(bilan);
    }

    public Bilan getBilanById(Long id) {
        return bilanRepository.findById(id).orElse(null);
    }

    public List<Bilan> getAllBilans() {
        return bilanRepository.findAll();
    }

    public void deleteBilan(Long id) {
        bilanRepository.deleteById(id);
    }


    public Bilan updateAllAnalysesAndBilan(Long id, BilanCreationDTO bilanCreationDTO) {
        Bilan bilan = bilanRepository.findById(id).orElse(null);
        if (bilan != null) {
            bilan.getAnalyses().clear();
            bilanRepository.save(bilan);
            return saveAllAnalysesAndBilan(bilanCreationDTO.analyses);
        }
        return null;
    }


    public Bilan saveAllAnalysesAndBilan(List<AnalyseDTO> analyses) {
        Bilan bilan = new Bilan();
        bilanRepository.save(bilan);

        analyses.forEach(analyse -> {
            switch (analyse.type) {
                case GLYCEMIE -> saveGlycemie((GlycemieDTO) analyse, bilan);


                case LIPIDIQUE -> {
                    // Downcasting de l'AnalyseDTO en HemoglobineDTO
                    LipidiqueDTO lipidiqueDTO = (LipidiqueDTO) analyse;

                    // Création d'une nouvelle instance de Hemoglobine
                    Lipidique lipidique = new Lipidique();

                    // Initialisation de l'instance avec les données du DTO
                    lipidique.setType(lipidiqueDTO.type.name());
                    lipidique.setCholesterol(lipidiqueDTO.cholesterol);
                    lipidique.setTriglycerides(lipidiqueDTO.triglycerides);
                    lipidique.setHdl(lipidiqueDTO.hdl);
                    lipidique.setLdl(lipidiqueDTO.ldl);
                    lipidique.setVldl(lipidiqueDTO.vldl);

                    // Enregistrement de l'instance dans la base de données
                    lipidique.setBilan(bilan);
                    analyseRepository.save(lipidique);
                    bilan.getAnalyses().add(lipidique);
                }

                case IONOGRAMME_SANGUIN -> {
                    // Downcasting de l'AnalyseDTO en HemoglobineDTO
                    IonogrammeSanguinDTO ionogrammeSanguinDTO = (IonogrammeSanguinDTO) analyse;

                    // Création d'une nouvelle instance de Hemoglobine
                    IonogrammeSanguin ionogrammeSanguin = new IonogrammeSanguin();

                    // Initialisation de l'instance avec les données du DTO
                    ionogrammeSanguin.setType(ionogrammeSanguinDTO.type.name());
                    ionogrammeSanguin.setSodium(ionogrammeSanguinDTO.sodium);
                    ionogrammeSanguin.setPotassium(ionogrammeSanguinDTO.potassium);
                    ionogrammeSanguin.setChlore(ionogrammeSanguinDTO.chlore);
                    ionogrammeSanguin.setReserve_alcaline(ionogrammeSanguinDTO.reserve_alcaline);
                    ionogrammeSanguin.setTrou_anionique(ionogrammeSanguinDTO.trou_anionique);
                    ionogrammeSanguin.setProtides_totaux(ionogrammeSanguinDTO.protides_totaux);

                    // Enregistrement de l'instance dans la base de données
                    ionogrammeSanguin.setBilan(bilan);
                    analyseRepository.save(ionogrammeSanguin);
                    bilan.getAnalyses().add(ionogrammeSanguin);
                }

                case TRANSAMINASE -> {
                    // Downcasting de l'AnalyseDTO en TransaminaseDTO
                    TransaminaseDTO transaminaseDTO = (TransaminaseDTO) analyse;

                    // Création d'une nouvelle instance de transaminase
                    Transaminase transaminase = new Transaminase();

                    // Initialisation de l'instance avec les données du DTO
                    transaminase.setType(transaminaseDTO.type.name());
                    transaminase.setSgot(transaminaseDTO.sgot);
                    transaminase.setSgpt(transaminaseDTO.sgpt);

                    // Enregistrement de l'instance dans la base de données
                    transaminase.setBilan(bilan);
                    analyseRepository.save(transaminase);
                    bilan.getAnalyses().add(transaminase);


                }
                case HEMOGLOBINE -> {
                    // Downcasting de l'AnalyseDTO en HemoglobineDTO
                    HemoglobineDTO hemoglobineDTO = (HemoglobineDTO) analyse;

                    // Création d'une nouvelle instance de Hemoglobine
                    Hemoglobine hemoglobine = new Hemoglobine();

                    // Initialisation de l'instance avec les données du DTO
                    hemoglobine.setType(hemoglobineDTO.type.name());
                    hemoglobine.setHemoglobine_glycosée(hemoglobineDTO.hemoglobine);
                    hemoglobine.setCreatinine(hemoglobineDTO.creatinine);
                    hemoglobine.setAcide_urique(hemoglobineDTO.acide_urique);

                    // Enregistrement de l'instance dans la base de données
                    hemoglobine.setBilan(bilan);
                    analyseRepository.save(hemoglobine);
                    bilan.getAnalyses().add(hemoglobine);
                }
            }
        });

        return bilanRepository.save(bilan);
    }


    private void saveGlycemie(GlycemieDTO analyse, Bilan bilan) {
        // Downcasting de l'AnalyseDTO en HemoglobineDTO
        GlycemieDTO glycemieDTO = analyse;


        // Création d'une nouvelle instance de Hemoglobine
        Glycemie glycemie = new Glycemie();

        // Initialisation de l'instance avec les données du DTO

        glycemie.setType(glycemieDTO.type.name());
        glycemie.setGlycemie(glycemieDTO.glycemie);

        // Enregistrement de l'instance dans la base de données
        glycemie.setBilan(bilan);
        analyseRepository.save(glycemie);
        bilan.getAnalyses().add(glycemie);
    }
}
