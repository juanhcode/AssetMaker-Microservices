package com.assetmaker.msvc.usuarios.controllers;

import com.assetmaker.msvc.usuarios.exceptions.BadRequestException;
import com.assetmaker.msvc.usuarios.persistance.entities.User;
import com.assetmaker.msvc.usuarios.services.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Lazy
    @Autowired
    @Qualifier("BD")
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdateUser(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            throw new BadRequestException("Revise los campos del usuario: " + user);
        } else if (user.getPassword().length() < 6) {
            throw new BadRequestException("La contraseña debe tener al menos 6 caracteres");
        }
        if (userService.getUserByEmail(user.getEmail()) != null) {
            throw new BadRequestException("El email ya se encuentra registrado");
        }
        userService.saveOrUpdateUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);

    }
}
