package com.example.clinique.Entity.Auth;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Setter
@Getter
@Entity
@Table

public class ConcreteUser extends User {
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
        // Implémentez la logique pour vérifier si le compte de l'utilisateur a expiré
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implémentez la logique pour vérifier si le compte de l'utilisateur est verrouillé
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implémentez la logique pour vérifier si les informations d'identification de l'utilisateur ont expiré
        return false;
    }

    @Override
    public boolean isEnabled() {
        // Implémentez la logique pour vérifier si le compte de l'utilisateur est activé
        return false;
    }
}


