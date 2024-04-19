package com.example.clinique.Entity.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Manager extends User {

    public Manager(String email, String password, String cin, String tele, String userName, Long id, Date dateOfBirth) {

        super(id,email,password,cin,tele,userName,"MANAGER",dateOfBirth);
    }
}
