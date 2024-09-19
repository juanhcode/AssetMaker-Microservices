package com.assetmaker.msvc.usuarios.services;

import com.assetmaker.msvc.usuarios.persistance.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    public List<User> getUsers();
    public void saveOrUpdateUser(User user);
    public User getUserByEmailAndPassword(String email, String password);
    void updateUser(Integer id, Map<String, Object> updates);
    void deleteUserById(Integer id);
    public Optional<User> getUserByEmail(String email);
    public Optional<User> getUserById(Integer id);
}

