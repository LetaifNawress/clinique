package com.example.clinique.Entity.Auth;

import io.swagger.v3.oas.annotations.info.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String cin;

    @Column(nullable = false, unique = true)
    private String tele;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String role;

    @Column(nullable = true)
    private Date dateOfBirth;



}
