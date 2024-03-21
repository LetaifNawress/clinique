package com.example.clinique.Entity.laboratoire;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "ionogramme_sanguin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class IonogrammeSanguin extends Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double sodium;
    private double potassium;
    private double chlore;
    private double reserve_alcaline;
    private double trou_anionique;
    private double protides_totaux;


}
