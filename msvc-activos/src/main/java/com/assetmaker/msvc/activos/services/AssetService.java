package com.assetmaker.msvc.activos.services;

import com.assetmaker.msvc.activos.persistance.entities.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    List<Asset> getAssets();
    void saveOrUpdateAsset(Asset asset);
    void deleteAssetById(Integer id);
    Optional<Asset> getAssetById(Integer id);
}
