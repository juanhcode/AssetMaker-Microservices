package com.assetmaker.msvc.portafolios.clients;

import com.assetmaker.msvc.portafolios.persistance.entities.Portfolio;
import com.assetmaker.msvc.portafolios.persistance.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@FeignClient(name = "msvc-usuarios", url = "${feign.client.url}")
public interface UserClientRest {
    @GetMapping("/users/{id}")
    Optional<User> getUserById(@PathVariable Integer id);
}
