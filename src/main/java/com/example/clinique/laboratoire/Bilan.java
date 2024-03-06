package com.example.clinique.laboratoire;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Bilan")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize


public class Bilan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Autres attributs du bilan

    @OneToMany(mappedBy = "bilan", cascade = CascadeType.ALL)
    private List<Glycemie> glycemies;

    @OneToMany(mappedBy = "bilan", cascade = CascadeType.ALL)
    private List<Hemoglobine> hemoglobines;

    @OneToMany(mappedBy = "bilan", cascade = CascadeType.ALL)
    private List<Lipidique> lipidiques;

    @OneToMany(mappedBy = "bilan", cascade = CascadeType.ALL)
    private List<Transaminases> transaminases;

    @OneToMany(mappedBy = "bilan", cascade = CascadeType.ALL)
    private List<IonogrammeSanguin> ionogrammeSanguin;



    // Getters et Setters

    //contructeur
    public Bilan(List<Glycemie> glycemies, List<Hemoglobine> hemoglobines, List<Lipidique> lipidiques, List<Transaminases> transaminases, List<IonogrammeSanguin> ionogrammeSanguin) {
        this.glycemies = glycemies;
        this.hemoglobines = hemoglobines;
        this.lipidiques = lipidiques;
        this.transaminases = transaminases;
        this.ionogrammeSanguin = ionogrammeSanguin;
    }
}
