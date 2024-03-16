package com.example.clinique.Entity.laboratoire;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "lipidique")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Lipidique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double cholesterol;
    private double triglycerides;
    private double hdl;
    private double ldl;
    private double vldl;

    @ManyToOne
    @JoinColumn(name = "idB", nullable = false)
    private Bilan bilan;

    public Lipidique(double cholesterol, double triglycerides, double hdl, double ldl, double vldl, Bilan bilan) {
        this.cholesterol = cholesterol;
        this.triglycerides = triglycerides;
        this.hdl = hdl;
        this.ldl = ldl;
        this.vldl = vldl;
        this.bilan = bilan;
    }
}
