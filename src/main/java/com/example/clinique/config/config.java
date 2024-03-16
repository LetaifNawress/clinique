package com.example.clinique.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Remplacez "http://localhost:4200" par l'URL de votre frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
