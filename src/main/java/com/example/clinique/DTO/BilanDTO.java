package com.example.clinique.DTO;


import com.example.clinique.laboratoire.Glycemie;
import com.example.clinique.laboratoire.Hemoglobine;
import com.example.clinique.laboratoire.IonogrammeSanguin;
import com.example.clinique.laboratoire.Transaminases;
import lombok.Data;

import java.util.List;

@Data
public class BilanDTO {
    private Long id;
    private List<Glycemie> glycemies;
    private List<Hemoglobine> hemoglobines;
    private List<IonogrammeSanguin> ionogrammeSanguin;
    //private List<Lipidique>  lipidiques;
    private List<Transaminases> transaminases;


}