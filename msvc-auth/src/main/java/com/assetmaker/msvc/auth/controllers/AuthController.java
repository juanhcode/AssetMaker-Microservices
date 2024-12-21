package com.assetmaker.msvc.auth.controllers;

import com.assetmaker.msvc.auth.helpers.JwtUtil;
import com.assetmaker.msvc.auth.persistance.models.User;
import com.assetmaker.msvc.auth.persistance.models.Usuario;
import com.assetmaker.msvc.auth.persistance.request.LoginReq;
import com.assetmaker.msvc.auth.persistance.response.ErrorRes;
import com.assetmaker.msvc.auth.persistance.response.LoginRes;
import com.assetmaker.msvc.auth.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        try {
            // Autenticación del usuario
            Usuario usuario = authService.authenticate(loginReq.getEmail(), loginReq.getPassword());
            System.out.println("Datos completos del usuario: " + usuario);

            // Convertir el objeto Usuario a User (si es necesario)
            User user = new User();
            user.setId(usuario.getId());
            user.setEmail(usuario.getEmail());
            user.setFirstName(usuario.getFirst_name());
            user.setLastName(usuario.getLast_names());
            user.setRiskProfile(usuario.getRisk_profile().toString());
            // No incluyas la contraseña a menos que sea estrictamente necesario

            // Generar el token JWT
            String jwt = jwtUtil.createToken(user);

            // Crear la respuesta de login
            LoginRes loginRes = new LoginRes(user.getEmail(), jwt);
            return ResponseEntity.ok(loginRes);
        } catch (UsernameNotFoundException e) {
            ErrorRes errorRes = new ErrorRes(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorRes);
        } catch (Exception e) {
            ErrorRes errorRes = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
        }
    }

}
