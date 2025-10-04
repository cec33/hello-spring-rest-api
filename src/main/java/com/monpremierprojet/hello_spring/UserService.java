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

    // 1. Pour la CREATION (POST)
    @CacheEvict(value = "users", allEntries = true) // Invalide le cache après la création
    public User save(User user) {
        System.out.println(">>> ENREGISTREMENT D UN NOUVEL UTILISATEUR en base de donnees");
        return userRepository.save(user);
    }

    // 2. Pour la MODIFICATION (PUT)
    @CacheEvict(value = "users", allEntries = true) // Invalide le cache après la modification
    public User update(Long id, User userDetails) {

        // 1. Trouver l utilisateur existant par son ID
        // .orElseThrow est une methode pratique pour gerer le cas ou l utilisateur n existe pas
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur non trouvé avec l'ID : " + id));

        // 2. Mettre a jour les champs utilisateur
        existingUser.setName(userDetails.getName());

        // 3. Enregistrer l entite mise a jour
        System.out.println(">>> Mise à jour de l'utilisateur " + id + " en base de données");

        return userRepository.save(existingUser);

    }

}
