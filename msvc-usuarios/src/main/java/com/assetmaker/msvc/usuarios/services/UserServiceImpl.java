package com.assetmaker.msvc.usuarios.services;

import com.assetmaker.msvc.usuarios.exceptions.ResourceNotFoundException;
import com.assetmaker.msvc.usuarios.persistance.entities.User;
import com.assetmaker.msvc.usuarios.persistance.repositories.UserRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("BD")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
    @Override
    @Transactional
    public void saveOrUpdateUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, Map<String, Object> updates) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            updates.forEach((key, value) -> {
                if (key.equals("risk_profile")) {
                    User.RiskProfile newRiskProfile = User.RiskProfile.valueOf(value.toString());
                    user.setRisk_profile(newRiskProfile);
                }else {
                    Field field = ReflectionUtils.findField(User.class, key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, user, value);
                }
            });
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User getUserByEmailAndPassword(String email, String password) {
        Optional<User>  optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (!encoder.matches(password, user.getPassword())) {
                throw new ResourceNotFoundException("Usuario o contraseña incorrectos");
            }
            return user;
        }
        return null;
    }
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
}
