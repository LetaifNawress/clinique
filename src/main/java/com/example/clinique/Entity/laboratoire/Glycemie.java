package com.example.clinique.Entity.laboratoire;


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
public class Glycemie extends Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double glycemie;
}
