package com.assetmaker.msvc.portafolios.services;

import com.assetmaker.msvc.portafolios.clients.UserClientRest;
import com.assetmaker.msvc.portafolios.persistance.entities.Portfolio;
import com.assetmaker.msvc.portafolios.persistance.entities.User;
import com.assetmaker.msvc.portafolios.persistance.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("BD")
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Portfolio> getPortfoliosByUserId(Integer idUser) {
        return portfolioRepository.findPortfoliosByIdUser(idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Portfolio> getPortfolioById(Integer Id) {
        return portfolioRepository.findById(Id);
    }

    @Override
    @Transactional
    public void saveOrUpdatePortfolio(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    @Override
    @Transactional
    public void deletePortfolioById(Integer id) {
        portfolioRepository.deleteById(id);
    }

}
