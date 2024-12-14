package com.assetmaker.msvc.activosportafolios.services;

import com.assetmaker.msvc.activosportafolios.persistance.entities.Asset;
import com.assetmaker.msvc.activosportafolios.persistance.entities.Contains;
import jakarta.persistence.Tuple;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AssetsPortfoliosService {
    List<Contains> getAssetsPortfolios();
    void saveOrUpdateAssetPortfolio(Contains assetPortfolio);
    void deleteByPortfolioIdAndAssetId(Integer idPortfolio, Integer idAsset);
    List<Contains> getAssetsPortfoliosByPortfolioId(Integer idPortfolio);
}
