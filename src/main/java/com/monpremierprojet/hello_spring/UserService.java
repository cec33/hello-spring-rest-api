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

    // 2. C'est ICI que l on met le Caching : Spring enveloppe cette methode.
    @Cacheable("users")
    public List<User> findAll() {
        System.out.println(">>> EXECUTION DE LA REQUETE BASE DE DONNEES (doit etre vu 1 seule fois)");
        return userRepository.findAll(); // Appel reel a al base de donnees
    }

}
