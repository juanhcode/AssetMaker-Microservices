package com.assetmaker.msvc.auth;
import com.assetmaker.msvc.auth.persistance.models.User;
import com.assetmaker.msvc.auth.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class AuthServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void authenticate_UserNotFound_ThrowsUsernameNotFoundException() {
        // Simula que no se encontró el usuario por email
        when(restTemplate.getForObject(anyString(), Mockito.eq(User.class))).thenReturn(null);

        // Ejecuta el método y espera que lance una excepción
        assertThrows(UsernameNotFoundException.class, () -> {
            authService.authenticate("notfound@example.com", "password");
        });
    }

    @Test
    public void authenticate_InvalidPassword_ThrowsUsernameNotFoundException() {
        // Simula que el usuario fue encontrado pero las credenciales son inválidas
        User user = new User();
        user.setEmail("test@example.com");
        when(restTemplate.getForObject(anyString(), Mockito.eq(User.class))).thenReturn(user).thenReturn(null);

        // Ejecuta el método y espera que lance una excepción
        assertThrows(UsernameNotFoundException.class, () -> {
            authService.authenticate("test@example.com", "invalidpassword");
        });
    }

    @Test
    public void authenticate_ErrorInUserService_ThrowsUsernameNotFoundException() {
        // Simula un error en la comunicación con el servicio de usuarios
        when(restTemplate.getForObject(anyString(), Mockito.eq(User.class))).thenThrow(new RuntimeException());

        // Ejecuta el método y espera que lance una excepción
        assertThrows(UsernameNotFoundException.class, () -> {
            authService.authenticate("error@example.com", "password");
        });
    }
}
