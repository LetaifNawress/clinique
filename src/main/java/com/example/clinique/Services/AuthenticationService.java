package com.example.clinique.Services;

import com.example.clinique.DTO.Authentication.AuthenticationRequest;
import com.example.clinique.DTO.Authentication.AuthenticationResponse;
import com.example.clinique.DTO.Authentication.UserRegistrationDTO;
import com.example.clinique.Entity.Auth.ConcreteUser;
import com.example.clinique.Repositories.UserRepository;
import com.example.clinique.config2.JwtService;
import com.example.clinique.Entity.Auth.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;

  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public void register(UserRegistrationDTO registrationDTO) {
    User user = new ConcreteUser();
    user.setEmail(registrationDTO.getEmail());
    user.setPassword(encryptPassword(registrationDTO.getPassword()));
    user.setCin(registrationDTO.getCin());
    user.setTele(registrationDTO.getTele());
    user.setUserName(registrationDTO.getUserName());
    user.setRole(registrationDTO.getRole());
    user.setDateOfBirth(registrationDTO.getDateOfBirth());

    userRepository.save(user);
  }

  private String encryptPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
  }






}
