package com.assetmaker.msvc.usuarios.persistance.repositories;

import com.assetmaker.msvc.usuarios.persistance.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
