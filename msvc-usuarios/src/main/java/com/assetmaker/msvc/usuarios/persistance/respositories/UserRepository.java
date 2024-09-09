package com.assetmaker.msvc.usuarios.persistance.respositories;

import com.assetmaker.msvc.usuarios.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
