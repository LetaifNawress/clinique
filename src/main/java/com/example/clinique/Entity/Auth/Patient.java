package com.example.clinique.Entity.Auth;

import com.example.clinique.Entity.Doc.DossierMedical;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.clinique.Entity.EtatDuJour;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Patient extends User {

    // Numéro d'assurance du patient
    @Column(nullable = true)
    private String insuranceNumber;

    @ManyToMany(mappedBy = "patients")
    private Set<Medecin> medecins = new HashSet<>();

    @Column(nullable = true)
    private String address;


    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private DossierMedical dossierMedical;


    // Date d'inscription du patient
    @Column(nullable = true)
    private Date registrationDate;
    @OneToMany(mappedBy = "patient")
    private Set<EtatDuJour> etatsDuJour = new HashSet<>();

    // Constructeur
    public Patient(String email, String password, String cin, String tele, String userName, Long id, Date dateOfBirth, String insuranceNumber, String medicalHistory, String address, String phoneNumber, Date registrationDate, Date lastVisitDate, String attendingPhysician, String allergies, String socialSecurityNumber) {
        super(id, email, password, cin, tele, userName, "PATIENT", dateOfBirth);
        this.insuranceNumber = insuranceNumber;

        this.address = address;

        this.registrationDate = registrationDate;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

