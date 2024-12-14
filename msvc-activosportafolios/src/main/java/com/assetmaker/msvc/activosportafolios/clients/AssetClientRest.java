package com.assetmaker.msvc.activosportafolios.clients;

import com.assetmaker.msvc.activosportafolios.persistance.entities.Asset;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "msvc-activos", url = "${feign.client.url2}")
public interface AssetClientRest {
    @GetMapping("/assets/{id}")
    Optional<Asset> getAssetById(@PathVariable Integer id);

}
