package com.example.clinique.config2;

import com.example.clinique.Entity.Auth.Authority;
import com.example.clinique.Entity.Auth.User;
import com.example.clinique.Repositories.Auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    String jwt = authHeader.substring(7);
    System.out.println(jwt);

    String username = jwtService.extractClaim(jwt, claims -> claims.getSubject());
    String role = jwtService.extractClaim(jwt, claims -> claims.get("Role", String.class));

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = getUserDetailsByUsername(username, role);
      if (userDetails != null) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }

  private UserDetails getUserDetailsByUsername(String username, String role) {
    Optional<User> userOptional = Optional.ofNullable(userRepository.findByUserName(username));
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      System.out.println("Role: " + role);
      System.out.println("Role utilisateur: " + user.getRole());

      List<Authority> authorities = new ArrayList<>();
      Authority authority = new Authority();
      authority.setAuthority(role);
      authorities.add(authority);


      user.setAuthorities(authorities);

      // Retourner les détails de l'utilisateur avec les nouvelles autorités
      return new org.springframework.security.core.userdetails.User(
              user.getUserName(),
              user.getPassword(),
              authorities.stream()
                      .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.getAuthority()))
                      .collect(Collectors.toList())
      );
    } else {
      throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username);
    }
  }
}
