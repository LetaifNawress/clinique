package com.example.clinique.Entity.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Admin extends User {
    // Constructor
    public Admin(String email, String password, String cin, String tele, String userName, Long id, Date dateOfBirth) {

        super(id,email,password,cin,tele,userName,"ADMIN",dateOfBirth);
    }
}
