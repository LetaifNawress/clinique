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
public class Lipidique extends Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double cholesterol;
    private double triglycerides;
    private double hdl;
    private double ldl;
    private double vldl;


}
