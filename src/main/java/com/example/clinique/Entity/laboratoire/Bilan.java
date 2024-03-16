package com.example.clinique.Entity.laboratoire;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bilan")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize


public class Bilan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idB;

    // Autres attributs du bilan
    @OneToMany(mappedBy = "bilan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Analyse> analyses = new ArrayList<>();
}
