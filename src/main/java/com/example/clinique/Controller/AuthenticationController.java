package com.example.clinique.Controller;

import com.example.clinique.DTO.Authentication.AuthenticationRequest;
import com.example.clinique.DTO.Authentication.AuthenticationResponse;
import com.example.clinique.DTO.Authentication.UserRegistrationDTO;
import com.example.clinique.Entity.Auth.User;
import com.example.clinique.Repositories.UserRepository;
import com.example.clinique.Services.AuthenticationService;
import com.example.clinique.config2.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        service.register(registrationDTO);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println("je suis ici : " + request.toString());
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);


        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String jwtToken = jwtService.generateToken(user);
            AuthenticationResponse response = AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentification échouée. Identifiants invalides.");
        }
    }
}





