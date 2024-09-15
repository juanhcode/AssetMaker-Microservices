//package com.assetmaker.msvc.auth.services;
//
//
//import com.assetmaker.msvc.auth.persistance.models.User;
//import com.assetmaker.msvc.auth.persistance.repositories.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private RestTemplate restTemplate;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//        this.restTemplate = new RestTemplate();
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        String urlEmail = "http://localhost:8080/users/user/"+ email;
//        User userByEmail = restTemplate.getForObject(urlEmail, User.class);
//        if (userByEmail != null) {
//            String url = "http://localhost:8080/users/validate/"+ userByEmail.getEmail() + "/"+ userByEmail.getPassword();
//            User userNew = restTemplate.getForObject(url, User.class);
//            if (userNew == null) {
//                throw new UsernameNotFoundException("User not found");
//            }
//            List<String> roles = new ArrayList<>();
//            roles.add("USER");
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(userNew.getEmail())
//                    .password(userNew.getPassword())
//                    .roles(roles.toArray(new String[0]))
//                    .build();
//        }else{
//            throw new UsernameNotFoundException("Datos incorrectos");
//        }
//    }
//}
