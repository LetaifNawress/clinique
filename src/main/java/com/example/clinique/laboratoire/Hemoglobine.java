package com.example.clinique.laboratoire;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "hemoglobine")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Hemoglobine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double hemoglobine_glycosée;
    private double creatinine;
    private double acide_urique;

    @ManyToOne
    @JoinColumn(name = "idB", nullable = false)
    private Bilan bilan;

    public Hemoglobine(double hemoglobine_glycosée, double creatinine, double acide_urique, Bilan bilan) {
        this.hemoglobine_glycosée = hemoglobine_glycosée;
        this.creatinine = creatinine;
        this.acide_urique = acide_urique;
        this.bilan = bilan;
    }
}
