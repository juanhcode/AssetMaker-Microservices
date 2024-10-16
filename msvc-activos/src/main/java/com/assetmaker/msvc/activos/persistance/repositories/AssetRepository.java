package com.assetmaker.msvc.activos.persistance.repositories;

import com.assetmaker.msvc.activos.persistance.entities.Asset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends CrudRepository<Asset, Integer> {

}
