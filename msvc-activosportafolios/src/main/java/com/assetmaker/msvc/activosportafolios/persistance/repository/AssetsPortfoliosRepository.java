package com.assetmaker.msvc.activosportafolios.persistance.repository;

import com.assetmaker.msvc.activosportafolios.persistance.entities.Asset;
import com.assetmaker.msvc.activosportafolios.persistance.entities.Contains;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface AssetsPortfoliosRepository extends CrudRepository <Contains, Integer> {
    List<Contains> findByIdPortfolio(Integer idPortfolio);
    List<Contains> findByIdAsset(Integer idAsset);
    Optional<Contains> findByIdPortfolioAndIdAsset(Integer idPortfolio, Integer idAsset);
}
