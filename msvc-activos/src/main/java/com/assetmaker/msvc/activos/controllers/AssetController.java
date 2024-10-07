package com.assetmaker.msvc.activos.controllers;

import com.assetmaker.msvc.activos.exceptions.ResourceNotFoundException;
import com.assetmaker.msvc.activos.persistance.entities.Asset;
import com.assetmaker.msvc.activos.services.AssetService;
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
@RequestMapping("/assets")
public class AssetController {
    @Lazy
    @Autowired
    @Qualifier("BD")
    private AssetService assetService;

    @GetMapping
    public ResponseEntity<?> getAssets() {
        List<Asset> assets = assetService.getAssets();
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable Integer id) {
        Optional<Asset> assetOptional = assetService.getAssetById(id);
        if (assetOptional.isEmpty()) {
            throw new ResourceNotFoundException("El activo con id: " + id + " no fue encontrado");
        }
        return ResponseEntity.ok(assetOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdateAsset(@RequestBody Asset asset) {
        assetService.saveOrUpdateAsset(asset);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(asset.getId())
                .toUri();
        return ResponseEntity.created(location).body(asset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAsset(@PathVariable Integer id, @RequestBody Asset asset) {
        Optional<Asset> assetOptional = assetService.getAssetById(id);
        if (assetOptional.isEmpty()) {
            throw new ResourceNotFoundException("El activo con id: " + id + " no fue encontrado");
        }
        assetService.saveOrUpdateAsset(asset);
        return ResponseEntity.ok(asset);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssetById(@PathVariable Integer id) {
        Optional<Asset> assetOptional = assetService.getAssetById(id);
        if (assetOptional.isEmpty()) {
            throw new ResourceNotFoundException("El activo con id: " + id + " no fue encontrado");
        }
        assetService.deleteAssetById(id);
        return ResponseEntity.ok("Activo eliminado con éxito");
    }
}
