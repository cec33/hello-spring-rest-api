package com.monpremierprojet.hello_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository; // On injecte le Repository JPA

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll(); // La méthode générée par Spring Data
    }


}
