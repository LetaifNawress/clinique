package com.example.clinique.Repositories.Auth;

import com.example.clinique.Entity.Auth.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


     User findByTele(String phoneNumber) ;


    User findByUserName(String username);


    Optional<User> findByEmail(String email);
}
