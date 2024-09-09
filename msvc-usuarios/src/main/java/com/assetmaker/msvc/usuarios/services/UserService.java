package com.assetmaker.msvc.usuarios.services;

import com.assetmaker.msvc.usuarios.persistance.entities.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public void saveOrUpdateUser(User user);
    public User getUserByEmail(String email);
}
