package com.assetmaker.msvc.activosportafolios.clients;

import com.assetmaker.msvc.activosportafolios.persistance.entities.Portfolio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "msvc-portafolios", url = "${feign.client.url1}")
public interface PortfolioClientRest {
    @GetMapping ("/portfolios/{id}")
    Optional<Portfolio> getPortfolioById(@PathVariable Integer id);
}
