package com.example.clinique.DTO;

import lombok.Data;

@Data
public class IonogrammeSanguinDTO {
    //ajout de l'attribut id
    private Long id;
    private double sodium;
    private double potassium;
    private double chlore;
    private double reserve_alcaline;
    private double trou_anionique;
    private double protides_totaux;

}
