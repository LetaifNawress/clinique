package com.example.clinique.Repositories;

import com.example.clinique.Entity.Auth.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserName(String username);


    Optional<User> findByEmail(String email);
}
