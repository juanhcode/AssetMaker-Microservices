package com.assetmaker.msvc.usuarios.controllers;

import com.assetmaker.msvc.usuarios.exceptions.BadRequestException;
import com.assetmaker.msvc.usuarios.exceptions.ResourceNotFoundException;
import com.assetmaker.msvc.usuarios.persistance.entities.User;
import com.assetmaker.msvc.usuarios.services.UserService;
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
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            throw new ResourceNotFoundException("El usuario con id: " + id + " no fue encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdateUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException("Revise los campos del usuario: " + user);
        } else if (user.getPassword().length() < 6) {
            throw new BadRequestException("La contraseña debe tener al menos 6 caracteres");
        } else if (userService.getUserByEmail(user.getEmail()).isPresent()) {
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

    @GetMapping("/validate/{email}/{password}")
    public ResponseEntity<?> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        User user = userService.getUserByEmailAndPassword(email, password);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user);
        } else {
            throw new ResourceNotFoundException("El usuario con email: " + email + " no fue encontrado");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody Map<String, Object> updates, BindingResult result) {
        Optional<User> userOptional = userService.getUserById(id);
        if (result.hasErrors()) {
            throw new BadRequestException("Revise los campos del usuario: " + updates);
        }
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            if (entry.getValue() == null || entry.getValue().toString().isEmpty() || entry.getValue().toString().isBlank()) {
                throw new BadRequestException("El campo " + entry.getKey() + " no puede ser nulo ni vacio");
            }
        }
        //Evalua que el campo risk_profile tenga un valor valido
        if (updates.containsKey("risk_profile") && !updates.get("risk_profile").toString().matches("Conservative|Moderate|Risky")) {
            throw new BadRequestException("El campo risk_profile debe tener un valor valido: Conservative, Moderate, Risky");
        }
        //Evalua que el email tenga un formato correcto
        if (updates.containsKey("email") && !updates.get("email").toString().matches("^(.+)@(.+)$")) {
            throw new BadRequestException("El email no tiene un formato correcto");
        } else if (updates.containsKey("email") && userService.getUserByEmail(updates.get("email").toString()).isPresent()) {
            throw new BadRequestException("El email ya se encuentra registrado");
        } else if (userOptional.isPresent()) {
            userService.updateUser(id, updates);
            return ResponseEntity.ok("Usuario actualizado con exito");
        } else {
            throw new ResourceNotFoundException("El usuario con id: " + id + " no fue encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Cuenta eliminada con exito");
        } else {
            throw new ResourceNotFoundException("El usuario con id: " + id + " no fue encontrado");
        }
    }
}
