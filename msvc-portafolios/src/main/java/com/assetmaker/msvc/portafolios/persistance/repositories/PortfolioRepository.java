package com.assetmaker.msvc.portafolios.persistance.repositories;

import com.assetmaker.msvc.portafolios.persistance.entities.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio, Integer> {
    List<Portfolio> findPortfoliosByIdUser(Integer idUser);
}
