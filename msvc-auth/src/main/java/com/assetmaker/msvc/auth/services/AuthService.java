package com.assetmaker.msvc.auth.services;

import com.assetmaker.msvc.auth.clients.UsuarioClientRest;
import com.assetmaker.msvc.auth.persistance.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioClientRest usuarioClientRest;

    public Usuario authenticate(String email, String password) throws UsernameNotFoundException {
        try {
            // Buscar usuario por email
            Usuario usuario = usuarioClientRest.getUserByEmail(email);
            System.out.println("Usuario encontrado: " + usuario);

            if (usuario != null) {
                // Validar contraseña
                Usuario userByEmail = usuarioClientRest.getUserByEmailAndPassword(email, password);
                if (userByEmail != null) {
                    return userByEmail; // Devuelve el objeto completo del usuario
                } else {
                    throw new UsernameNotFoundException("Credenciales inválidas");
                }
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error en la comunicación con el servicio de usuarios", e);
        }
    }
}

