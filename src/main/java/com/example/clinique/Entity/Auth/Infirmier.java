package com.example.clinique.Entity.Auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Infirmier extends User {

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String department;


    public Infirmier (String email, String password, String cin, String tele, String userName, Long id, Date dateOfBirth, String title, String department) {
        super(id,email,password,cin,tele,userName,"INFERMIER",dateOfBirth);
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
