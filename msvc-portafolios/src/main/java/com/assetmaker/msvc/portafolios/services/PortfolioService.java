package com.assetmaker.msvc.portafolios.services;

import com.assetmaker.msvc.portafolios.persistance.entities.Portfolio;
import com.assetmaker.msvc.portafolios.persistance.entities.User;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {
    List<Portfolio> getPortfoliosByUserId(Integer idUser);
    Optional<Portfolio> getPortfolioById(Integer id);
    void saveOrUpdatePortfolio(Portfolio portfolio);
    void deletePortfolioById(Integer id);
}
