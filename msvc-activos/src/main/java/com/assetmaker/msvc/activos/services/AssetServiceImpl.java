package com.assetmaker.msvc.activos.services;

import com.assetmaker.msvc.activos.persistance.entities.Asset;
import com.assetmaker.msvc.activos.persistance.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("BD")
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Asset> getAssets() {
        return (List<Asset>) assetRepository.findAll();
    }

    @Override
    @Transactional
    public void saveOrUpdateAsset(Asset asset) {
        assetRepository.save(asset);
    }

    @Override
    @Transactional
    public void deleteAssetById(Integer id) {
        assetRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Asset> getAssetById(Integer id) {
        return assetRepository.findById(id);
    }
}
