package com.assetmaker.msvc.auth;

import com.assetmaker.msvc.auth.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService authService;

    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Si no necesitas formularios, puedes deshabilitar CSRF
                .authorizeRequests()
                .requestMatchers("/rest/auth/**").permitAll() // Rutas públicas
                .anyRequest().authenticated() // Rutas privadas
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Stateless para JWT
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String email = authentication.getName();
                String password = authentication.getCredentials().toString();

                var userDetails = authService.authenticate(email, password);

                return new UsernamePasswordAuthenticationToken(
                        userDetails.getEmail(),
                        password
                );
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encriptación de contraseñas
    }
}
