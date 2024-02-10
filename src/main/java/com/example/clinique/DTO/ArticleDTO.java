package com.example.clinique.DTO;

import lombok.Data;

@Data
public class ArticleDTO {
    private Long id;
    private String nom;
    private String description;
    private Long fournisseurId;
    private Long categorieId;


}
