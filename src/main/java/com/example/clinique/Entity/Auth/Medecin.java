package com.example.clinique.Entity.Auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@Entity
public class Medecin extends User {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String department;
    public Medecin(String email, String password, String cin, String tele, String userName, Long id, Date dateOfBirth, String title, String department) {
        super(id,email,password,cin,tele,userName,"DOCTOR",dateOfBirth);
        this.title = title;
        this.department = department;
    }
}
