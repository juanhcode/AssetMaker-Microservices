package com.assetmaker.msvc.activos.services;

import com.assetmaker.msvc.activos.persistance.entities.Asset;
import com.assetmaker.msvc.activos.persistance.repositories.AssetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetServiceImplTest {
    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImpl assetService;

    @Test
    void getAssets() {
        when(assetRepository.findAll()).thenReturn(List.of(new Asset(), new Asset()));
        List<Asset> assets = assetService.getAssets();
        assertNotNull(assets);
    }

    @Test
    void saveOrUpdateAsset() {
//        Asset asset = new Asset();
//        asset.setName("Asset 1");
//        asset.setDescription("Asset 1 description");
//        asset.setAssetType("Type 1");
//
//        when(assetRepository.save(Mockito.any(Asset.class))).thenReturn(asset);
//
//        assetService.saveOrUpdateAsset(asset);
//        assertNotNull(asset);
    }

    //Test para actualizar un activo
    @Test
    void updateAsset() {
//        Asset asset = new Asset();
//        asset.setName("Asset 2");
//        asset.setDescription("Asset 2 description");
//        asset.setAssetType("Type 2");
//
//        Optional<Asset> assetOptional = assetService.getAssetById(1);
//        assertNotNull(assetOptional);
//        when(assetRepository.save(Mockito.any(Asset.class))).thenReturn(asset);
//
//        assetService.saveOrUpdateAsset(asset);
//        assertNotNull(asset);

    }

    @Test
    void deleteAsset() {
        assetService.deleteAssetById(1);
        Mockito.verify(assetRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void getAssetById() {
//        Asset asset = new Asset();
//        asset.setName("Asset 1");
//        asset.setDescription("Asset 1 description");
//        asset.setAssetType("Type 1");
//
//        when(assetRepository.findById(1)).thenReturn(Optional.of(asset));
//        Optional<Asset> assetOptional = assetService.getAssetById(1);
//        assertNotNull(assetOptional);
    }

}
