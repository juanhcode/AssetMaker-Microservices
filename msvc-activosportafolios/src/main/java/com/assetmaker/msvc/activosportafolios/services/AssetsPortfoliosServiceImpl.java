package com.assetmaker.msvc.activosportafolios.services;

import com.assetmaker.msvc.activosportafolios.persistance.entities.Asset;
import com.assetmaker.msvc.activosportafolios.persistance.entities.Contains;
import com.assetmaker.msvc.activosportafolios.persistance.repository.AssetsPortfoliosRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("BD")
public class AssetsPortfoliosServiceImpl implements AssetsPortfoliosService {
    @Autowired
    private AssetsPortfoliosRepository assetsPortfoliosRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Contains> getAssetsPortfolios() {
        return (List<Contains>) assetsPortfoliosRepository.findAll();
    }

    @Override
    @Transactional
    public void saveOrUpdateAssetPortfolio(Contains assetPortfolio) {
        assetsPortfoliosRepository.save(assetPortfolio);
    }

    @Override
    @Transactional
    public void deleteByPortfolioIdAndAssetId(Integer idPortfolio, Integer idAsset) {
        Optional <Contains> assetPortfolio = assetsPortfoliosRepository.findByIdPortfolioAndIdAsset(idPortfolio, idAsset);
        assetPortfolio.ifPresent(contains -> assetsPortfoliosRepository.delete(contains));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contains> getAssetsPortfoliosByPortfolioId(Integer idPortfolio) {
        return  assetsPortfoliosRepository.findByIdPortfolio(idPortfolio);
    }
}
