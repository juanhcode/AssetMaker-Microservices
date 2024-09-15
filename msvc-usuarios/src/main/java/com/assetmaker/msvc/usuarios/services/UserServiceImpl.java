package com.assetmaker.msvc.usuarios.services;

import com.assetmaker.msvc.usuarios.exceptions.ResourceNotFoundException;
import com.assetmaker.msvc.usuarios.persistance.entities.User;
import com.assetmaker.msvc.usuarios.persistance.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("BD")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll().
                stream().map(userEntity -> {
                    User user = new User();
                    user.setId(userEntity.getId());
                    user.setFirst_name(userEntity.getFirst_name());
                    user.setLast_names(userEntity.getLast_names());
                    user.setEmail(userEntity.getEmail());
                    user.setRisk_profile(userEntity.getRisk_profile());
                    return user;
                }).collect(Collectors.toList());
        return users;
    }
    @Override
    public void saveOrUpdateUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !encoder.matches(password, user.getPassword())) {
            throw new ResourceNotFoundException("Usuario o contraseña incorrectos");
        }
        return user;
    }

}
