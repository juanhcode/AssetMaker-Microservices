package com.assetmaker.msvc.activosportafolios.controllers;

import com.assetmaker.msvc.activosportafolios.clients.AssetClientRest;
import com.assetmaker.msvc.activosportafolios.clients.PortfolioClientRest;
import com.assetmaker.msvc.activosportafolios.exceptions.ResourceNotFoundException;
import com.assetmaker.msvc.activosportafolios.persistance.entities.Asset;
import com.assetmaker.msvc.activosportafolios.persistance.entities.Contains;
import com.assetmaker.msvc.activosportafolios.persistance.entities.Portfolio;
import com.assetmaker.msvc.activosportafolios.services.AssetsPortfoliosService;
import feign.FeignException;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/assetsportfolios")
public class AssestsPortfoliosController {
    @Lazy
    @Autowired
    @Qualifier("BD")
    private AssetsPortfoliosService assetsPortfoliosService;

    @Lazy
    @Autowired
    private AssetClientRest assetClientRest;

    @Lazy
    @Autowired
    private PortfolioClientRest portfolioClientRest;

    @GetMapping
    public ResponseEntity<?> getAssetsPortfolios() {
        return ResponseEntity.ok(assetsPortfoliosService.getAssetsPortfolios());
    }

    @GetMapping("/assetslist/{idPortfolio}")
    public ResponseEntity<?> getAssetsPortfoliosById(@PathVariable Integer idPortfolio) {
        try {
            Optional<Portfolio> portfolioOptional = portfolioClientRest.getPortfolioById(idPortfolio);
            if (portfolioOptional.isEmpty()) {
                throw new ResourceNotFoundException("El portafolio con id: " + idPortfolio + " no fue encontrado");
            }
            List<Contains> assetsPortfolios = assetsPortfoliosService.getAssetsPortfoliosByPortfolioId(idPortfolio);
            List<Asset> assets = assetsPortfolios.stream()
                    .map(contains -> assetClientRest.getAssetById(contains.getIdAsset()).orElse(null))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(assets);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("El portafolio con id: " + idPortfolio + " no fue encontrado");
        } catch (Exception e) {
            System.out.println("**************************** Ha ocurrido un error: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Ha ocurrido un error en el servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> saveAssetsPortfolios(@RequestBody Contains contains) {
        assetsPortfoliosService.saveOrUpdateAssetPortfolio(contains);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contains.getId())
                .toUri();
        return ResponseEntity.created(location).body(contains);
    }

    @DeleteMapping("/{idPortfolio}/{idAsset}")
    public ResponseEntity<?> deleteAssetsPortfoliosById(@PathVariable Integer idPortfolio, @PathVariable Integer idAsset) {
        Optional<Portfolio> portfolioOptional = portfolioClientRest.getPortfolioById(idPortfolio);
        if (portfolioOptional.isEmpty()) {
            throw new ResourceNotFoundException("El portafolio con id: " + idPortfolio + " no fue encontrado");
        }
        Optional<Asset> assetOptional = assetClientRest.getAssetById(idAsset);
        if (assetOptional.isEmpty()) {
            throw new ResourceNotFoundException("El activo con id: " + idAsset + " no fue encontrado");
        }

        assetsPortfoliosService.deleteByPortfolioIdAndAssetId(idPortfolio, idAsset);
        return ResponseEntity.noContent().build();
    }

}
