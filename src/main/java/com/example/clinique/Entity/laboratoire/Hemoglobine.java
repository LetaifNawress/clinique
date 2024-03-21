package com.example.clinique.Entity.laboratoire;



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
public class Hemoglobine extends Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double hemoglobine_glycosée;
    private double creatinine;
    private double acide_urique;


}
