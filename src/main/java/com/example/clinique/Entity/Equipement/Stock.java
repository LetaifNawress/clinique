package com.example.clinique.Entity.Equipement;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "article_id")
    private Article article;
    private Integer quantite;
    private Integer seuilMin;
    private Integer seuilMax;
    private String emplacement;

}