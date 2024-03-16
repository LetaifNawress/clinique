// LaboratoireService.java
package com.example.clinique.Services;

import com.example.clinique.DTO.BilanDTO;
import com.example.clinique.Entity.laboratoire.Bilan;
import com.example.clinique.Repositories.BilanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoireService {
    @Autowired
    private BilanRepository bilanRepository;

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

    public Bilan updateBilan(Long id, BilanDTO bilanDTO) {
        // Implémentez la mise à jour du bilan en fonction des données du DTO


        return null;
    }
}
