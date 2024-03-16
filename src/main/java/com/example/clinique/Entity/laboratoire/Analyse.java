package com.example.clinique.Entity.laboratoire;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Analyse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getters et Setters
}
