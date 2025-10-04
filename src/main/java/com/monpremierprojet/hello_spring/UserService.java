package com.monpremierprojet.hello_spring;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    @CacheEvict(value = "users", allEntries = true) // Invalide le cache après la création
    public User save(User user) {
        System.out.println(">>> ENREGISTREMENT D UN NOUVEL UTILISATEUR en base de donnees");
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true) // Invalide le cache après la modification
    public User update(Long id, User userDetails) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur non trouvé avec l'ID : " + id));

        existingUser.setName(userDetails.getName());

        System.out.println(">>> Mise à jour de l'utilisateur " + id + " en base de données");

        return userRepository.save(existingUser);

    }

    // Suppression d'utilisateur
    @CacheEvict(value="users", allEntries = true)
    public void delete(Long id) {
        // Optionnel : verifier si l'utilisateur existe avant de supprimer
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("Utilisateur non trouvé avec l'ID : " + id);
        }

        System.out.println(">>> Suppression de l'utilisateur " + id + " en base de données");
        userRepository.deleteById(id);

    }

}
