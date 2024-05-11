package com.example.clinique.Entity.Auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class userT {
    @Id
    private String id;
    private String phoneNumber;
}
