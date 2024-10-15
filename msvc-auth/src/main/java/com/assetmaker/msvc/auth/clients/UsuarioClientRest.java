package com.assetmaker.msvc.auth.clients;


import com.assetmaker.msvc.auth.persistance.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-usuarios", url = "http://localhost:8080")
public interface UsuarioClientRest {


    @GetMapping("/users/user/{email}")
    Usuario getUserByEmail(@PathVariable String email);


    @GetMapping("/users/validate/{email}/{password}")
    Usuario getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password);
}
