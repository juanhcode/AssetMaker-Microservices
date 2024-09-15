package com.assetmaker.msvc.usuarios.services;

import com.assetmaker.msvc.usuarios.persistance.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    void saveOrUpdateUser(User user);
    void updateUser(Integer id, Map<String, Object> updates);
    void deleteUserById(Integer id);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserById(Integer id);
}