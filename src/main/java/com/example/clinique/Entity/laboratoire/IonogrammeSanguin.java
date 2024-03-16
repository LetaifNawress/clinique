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
public class IonogrammeSanguin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double sodium;
    private double potassium;
    private double chlore;
    private double reserve_alcaline;
    private double trou_anionique;
    private double protides_totaux;

    @ManyToOne
    @JoinColumn(name = "idB", nullable = false)
    private Bilan bilan;

    public IonogrammeSanguin(double sodium, double potassium, double chlore, double reserve_alcaline, double trou_anionique, double protides_totaux, Bilan bilan) {
        this.sodium = sodium;
        this.potassium = potassium;
        this.chlore = chlore;
        this.reserve_alcaline = reserve_alcaline;
        this.trou_anionique = trou_anionique;
        this.protides_totaux = protides_totaux;
        this.bilan = bilan;
    }
}
