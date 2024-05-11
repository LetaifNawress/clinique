package com.example.clinique.Repositories.Auth;

import com.example.clinique.Entity.Auth.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByTele(String phoneNumber);
    User findByUserName(String username);
    Optional<User> findByEmail(String email);
}