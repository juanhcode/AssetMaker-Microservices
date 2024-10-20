package com.assetmaker.msvc.portafolios.controllers;

import com.assetmaker.msvc.portafolios.clients.UserClientRest;
import com.assetmaker.msvc.portafolios.exceptions.ResourceNotFoundException;
import com.assetmaker.msvc.portafolios.persistance.entities.Portfolio;
import com.assetmaker.msvc.portafolios.persistance.entities.User;
import com.assetmaker.msvc.portafolios.services.PortfolioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
    @Lazy
    @Autowired
    @Qualifier("BD")
    private PortfolioService portfolioService;

    @Autowired
    @Lazy
    private UserClientRest userClientRest;

    @GetMapping("/users/{idUser}")
    public ResponseEntity<?> getPortfoliosByUserById(@PathVariable Integer idUser) {
        try {
            Optional<User> userOptional = userClientRest.getUserById(idUser);
            List<Portfolio> portfolios = portfolioService.getPortfoliosByUserId(userOptional.get().getId());
            return ResponseEntity.ok(portfolios);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("El usuario con id: " + idUser + " no fue encontrado");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Ha ocurrido un error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPortfolioById(@PathVariable Integer id) {
        Optional<Portfolio> portfolioOptional = portfolioService.getPortfolioById(id);
        if (portfolioOptional.isPresent()) {
            return ResponseEntity.ok(portfolioOptional.get());
        } else {
            throw new ResourceNotFoundException("El portafolio con id: " + id + " no fue encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> savePortfolio(@RequestBody Portfolio portfolio) {
        portfolioService.saveOrUpdatePortfolio(portfolio);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(portfolio.getId())
                .toUri();
        return ResponseEntity.created(location).body(portfolio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePortfolio(@PathVariable Integer id, @RequestBody Portfolio portfolio) {
        Optional<Portfolio> portfolioOptional = portfolioService.getPortfolioById(id);
        if (portfolioOptional.isEmpty()) {
            throw new ResourceNotFoundException("El portafolio con id: " + id + " no fue encontrado");
        }
        portfolio.setId(id);
        portfolioService.saveOrUpdatePortfolio(portfolio);
        return ResponseEntity.ok(portfolio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePortfolioById(@PathVariable Integer id) {
        Optional<Portfolio> portfolioOptional = portfolioService.getPortfolioById(id);
        if (portfolioOptional.isEmpty()) {
            throw new ResourceNotFoundException("El portafolio con id: " + id + " no fue encontrado");
        }
        portfolioService.deletePortfolioById(id);
        return ResponseEntity.ok("Portafolio eliminado con éxito");
    }


}
