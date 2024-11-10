package com.assetmaker.msvc.auth.services;

import com.assetmaker.msvc.auth.clients.UsuarioClientRest;
import com.assetmaker.msvc.auth.persistance.models.User;
import com.assetmaker.msvc.auth.persistance.models.Usuario;
import com.assetmaker.msvc.auth.persistance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UsuarioClientRest usuarioClientRest;


    private final UserRepository userRepository;
    private RestTemplate restTemplate;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.restTemplate = new RestTemplate();
    }

    public UserDetails authenticate(String email, String password) throws UsernameNotFoundException {
        try {
            System.out.println("Hola");
            Usuario usuario = usuarioClientRest.getUserByEmail(email);
            System.out.println("Usuario: " + usuario);
            //String urlEmail = "http://localhost:8080/users/email/" + email;
            //User userByEmail = restTemplate.getForObject(urlEmail, User.class);

            if (usuario != null) {
                Usuario userByEmail = usuarioClientRest.getUserByEmailAndPassword(email, password);
//                String url = "http://localhost:8080/users/validate/" + userByEmail.getEmail() + "/" + password;
//                User validatedUser = restTemplate.getForObject(url, User.class);
                if (userByEmail != null) {
                    System.out.println("EXISTES Y TE AUTENTIFICASTE");
                    List<String> roles = new ArrayList<>();
                    roles.add("USER");
                    return org.springframework.security.core.userdetails.User.builder()
                            .username(userByEmail.getEmail())
                            .password(userByEmail.getPassword())
                            .roles(roles.toArray(new String[0]))
                            .build();
                } else {
                    throw new UsernameNotFoundException("Credenciales inválidas");
                }
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error en la comunicación con el servicio de usuarios");
        }
    }

}

