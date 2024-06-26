package com.example.clinique.Entity.Auth;

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
public class Medecin extends User {
    @ManyToMany
    @JoinTable(
            name = "medecin_patient",
            joinColumns = @JoinColumn(name = "medecin_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<Patient> patients = new HashSet<>();


    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String department;
    public Medecin(String email, String password, String cin, String tele, String userName, Long id, Date dateOfBirth, String title, String department) {
        super(id,email,password,cin,tele,userName,"DOCTOR",dateOfBirth);
        this.title = title;
        this.department = department;
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
