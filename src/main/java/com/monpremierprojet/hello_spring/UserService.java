package com.monpremierprojet.hello_spring;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 1. Indispensable pour l injection et l AOP (Caching)
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable("users")
    public List<User> findAll() {
        System.out.println(">>> EXECUTION DE LA REQUETE BASE DE DONNEES (doit etre vu 1 seule fois)");
        return userRepository.findAll(); // Appel reel a al base de donnees
    }

    // Logique pour sauvegarder un nouvel utilisateur
    public User save(User user) {
        System.out.println(">>> ENREGISTREMENT D UN NOUVEL UTILISATEUR en base de donnees");
        return userRepository.save(user);
    }

}
