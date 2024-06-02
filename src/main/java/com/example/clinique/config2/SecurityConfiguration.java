package com.example.clinique.config2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/auth/**", "/dossier-medical/**", "/recep/**"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(HttpMethod.POST,"/equipements/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET,"/equipements/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/equipements/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/equipements/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST,"/infer/**").hasRole("INFERMIER")
                        .requestMatchers(HttpMethod.GET,"/infer/**").hasRole("INFERMIER")
                        .requestMatchers(HttpMethod.DELETE,"/infer/**").hasRole("INFERMIER")
                        .requestMatchers(HttpMethod.PUT,"/infer/**").hasRole("INFERMIER")
                        .requestMatchers(HttpMethod.POST, "/bilans/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/bilans/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/bilans/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/bilans/**").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return http.build();}

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod(GET);
        config.addAllowedMethod(POST);
        config.addAllowedMethod(PUT);
        config.addAllowedMethod(DELETE);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
