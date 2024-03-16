package com.example.clinique.Entity.laboratoire;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "transaminases")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Transaminases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double sgpt;
    private double sgot;

    @ManyToOne
    @JoinColumn(name = "idB", nullable = false)
    private Bilan bilan;

    public Transaminases(double sgpt, double sgot, Bilan bilan) {
        this.sgpt = sgpt;
        this.sgot = sgot;
        this.bilan = bilan;
    }
}
