package com.example.clinique.laboratoire;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "glycemie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Glycemie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double glycemie;


    @ManyToOne
    @JoinColumn(name = "idB", nullable = false)
    private Bilan bilan;

    public Glycemie(double glycemie, Bilan bilan) {
        this.glycemie = glycemie;
        this.bilan = bilan;
    }
}
