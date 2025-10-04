package com.monpremierprojet.hello_spring;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // Injecter le Service, non plus le Repository
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll(); // Appelle la methode du Service qui est mise en cache
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    // Nouvel endpoint pour modifier un utilisateur existant
    @PutMapping("/users/{id}")
    public User updateUser(
            @PathVariable Long id, // Récupere l'ID de l'url
            @RequestBody User userDetails ) { // Récupere le corps JSON pour les nouvelles donnees
        return userService.update(id, userDetails);
    }

}
