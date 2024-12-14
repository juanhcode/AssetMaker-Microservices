package com.assetmaker.msvc.usuarios.services;

import com.assetmaker.msvc.usuarios.persistance.entities.User;
import com.assetmaker.msvc.usuarios.persistance.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));
        List<User> users = userService.getUsers();
        assertNotNull(users);
    }

    @Test
    void saveOrUpdateUser() {
        User user = new User();
        user.setFirst_name("John");
        user.setLast_names("Doe");
        user.setEmail("jhond@gmail.com");
        user.setPassword("123456");
        user.setRisk_profile(User.RiskProfile.Risky);

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        userService.saveOrUpdateUser(user);
        assertNotNull(user);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setFirst_name("John");
        user.setLast_names("Doe");
        user.setEmail("jhond@gmail.com");
        user.setPassword("123456");
        user.setRisk_profile(User.RiskProfile.Risky);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        userService.updateUser(1, Map.of("first_name", "Jane", "last_names", "Clent", "email",
                "Jclent@outlook.com", "password", "123456", "risk_profile", "Conservative"));

        assertNotNull(user);
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(1);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void getUserByEmail() {
        User user = new User();
        user.setFirst_name("John");
        user.setLast_names("Doe");
        user.setEmail("jhond@gmail.com");
        user.setPassword("123456");
        user.setRisk_profile(User.RiskProfile.Risky);

        when(userRepository.findByEmail("jhond@gmail.com")).thenReturn(Optional.of(user));

        Optional<User> userOptional = userService.getUserByEmail("jhond@gmail.com");
        assertNotNull(userOptional);
    }

    @Test
    void getUserByEmailAndPassword() {
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setFirst_name("John");
        user.setLast_names("Doe");
        user.setEmail("jhond@gmail.com");
        user.setPassword("123456");
        user.setRisk_profile(User.RiskProfile.Risky);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> userOptional = userService.getUserById(1);
        assertNotNull(userOptional);
    }
}